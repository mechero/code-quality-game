package com.thepracticaldeveloper.devgame.modules.code;

import java.util.StringTokenizer;

public class CodeDetails {
    private final String name;

    public CodeDetails(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CodeDetails fromCodeChain(final String codeChain) {
        final StringTokenizer tokenizer = new StringTokenizer(codeChain, "^T^", false);
        String name = null;
        while (tokenizer.hasMoreElements()) {
            final String token = tokenizer.nextToken();
            final String tokenKey = token.substring(0, token.indexOf('='));
            final String tokenValue = token.substring(token.indexOf('=') + 1);
            switch (tokenKey) {
                case "name": {
                    name = tokenValue;
                    break;
                }
            }
        }
        return new CodeDetails(name);
    }
}
