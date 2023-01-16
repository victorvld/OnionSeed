package io.essentials.adapter.encoder;

import io.essentials.domain.usecases.encoder.PasswordEncoder;
import org.apache.commons.codec.digest.DigestUtils;

public class Sha256Encoder implements PasswordEncoder {
    @Override
    public String encode(final String str) {
        return DigestUtils.sha256Hex(str);
    }
}
