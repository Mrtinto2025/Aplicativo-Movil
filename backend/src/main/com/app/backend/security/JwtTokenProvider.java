package com.app.backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private Long jwtExpirationInMs;

    private static final ObjectMapper mapper = new ObjectMapper();

    private String base64UrlEncode(String s) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(s.getBytes(StandardCharsets.UTF_8));
    }

    private String hmacSha256(String data, String secret) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] sig = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().withoutPadding().encodeToString(sig);
    }

    public String generateToken(Authentication authentication) {
        try {
            String username = authentication.getName();
            long now = System.currentTimeMillis();
            long exp = now + jwtExpirationInMs;

            Map<String, Object> header = new HashMap<>();
            header.put("alg", "HS256");
            header.put("typ", "JWT");

            Map<String, Object> payload = new HashMap<>();
            payload.put("sub", username);
            payload.put("iat", now / 1000);
            payload.put("exp", exp / 1000);

            String headerJson = mapper.writeValueAsString(header);
            String payloadJson = mapper.writeValueAsString(payload);

            String headerB64 = base64UrlEncode(headerJson);
            String payloadB64 = base64UrlEncode(payloadJson);

            String signingInput = headerB64 + "." + payloadB64;
            String signature = hmacSha256(signingInput, jwtSecret);

            return signingInput + "." + signature;
        } catch (Exception e) {
            return null;
        }
    }

    public String getUsernameFromJWT(String token) {
        try {
            if (token == null) return null;
            String[] parts = token.split("\\.");
            if (parts.length < 2) return null;
            String payloadB64 = parts[1];
            byte[] decoded = Base64.getUrlDecoder().decode(payloadB64);
            Map<?, ?> payload = mapper.readValue(decoded, Map.class);
            Object sub = payload.get("sub");
            return sub != null ? sub.toString() : null;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validateToken(String token) {
        try {
            if (token == null) return false;
            String[] parts = token.split("\\.");
            if (parts.length != 3) return false;
            String signingInput = parts[0] + "." + parts[1];
            String signature = parts[2];
            String expected = hmacSha256(signingInput, jwtSecret);
            if (!expected.equals(signature)) return false;

            // check expiration
            byte[] decoded = Base64.getUrlDecoder().decode(parts[1]);
            Map<?, ?> payload = mapper.readValue(decoded, Map.class);
            Object expObj = payload.get("exp");
            if (expObj != null) {
                long exp = Long.parseLong(expObj.toString()) * 1000L;
                if (System.currentTimeMillis() > exp) return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
