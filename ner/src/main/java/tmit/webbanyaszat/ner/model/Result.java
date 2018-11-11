package tmit.webbanyaszat.ner.model;

import java.util.Objects;

public class Result {

    private String word;
    private String wordClass;

    public Result(String word, String wordClass) {
        this.word = word;
        this.wordClass = wordClass;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWordClass() {
        return wordClass;
    }

    public void setWordClass(String wordClass) {
        this.wordClass = wordClass;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(word, result.word) &&
                Objects.equals(wordClass, result.wordClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, wordClass);
    }

    @Override
    public String toString() {
        return "Result{" +
                "word='" + word + '\'' +
                ", wordClass='" + wordClass + '\'' +
                '}';
    }
}
