package io.victorvld.adapter.encoder;

import io.victorvld.domain.usecases.encoder.PasswordEncoder;
import org.apache.commons.codec.digest.DigestUtils;

public class Sha256Encoder implements PasswordEncoder {
    @Override
    public String encode(final String str) {
        return DigestUtils.sha256Hex(str);
    }
}
