package com.globallogic;

import java.util.Set;

public class Key {
    private static final Character[] availableCharacters = {'L', 'O', 'G', 'I', 'C'};
    private final Set<Character> chars;
    private final int wordLength;
    private final int charsCode;

    public Key(Set<Character> chars, int wordLength) {
        this.chars = chars;
        this.wordLength = wordLength;
        this.charsCode = calculateCharsCode(chars);
    }

    private int calculateCharsCode(Set<Character> chars) {
        int code = 0;
        int charCodeValue = 1;
        for (int i = 0; i < availableCharacters.length; ++i) {
            if (chars.contains(availableCharacters[i]) ||
                    chars.contains(Character.toLowerCase(availableCharacters[i]))) {
                code += charCodeValue;
            }
            charCodeValue <<= 1;
        }
        return code;
    }

    @Override
    public int hashCode() {
        return charsCode + wordLength * 64;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Key other = (Key) obj;
        if (charsCode != other.charsCode)
            return false;
        if (wordLength != other.wordLength)
            return false;
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{(");
        boolean first = true;
        for (Character ch : chars) {
            if (!first) {
                sb.append(",");
            }
            sb.append(ch);
            first = false;
        }
        sb.append("), ");
        sb.append(wordLength);
        sb.append("}");
        return sb.toString();
    }
}

