import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 1) Есть какой-то текст (текст задаете самостоятельно).
 * Найти все слова "cat" и посчитать их количество
 * 2) Проверить, существуют ли предложения, у которых первое слово начинается с маленькой буквы.
 * Использовать текст из задачи 1
 * 3) Проверить, существуют ли слова в тексте, в которых буква "а" (или любая другая)
 * повторяется несколько раз подряд. Например, Ваася
 */

public class Main {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        try(FileReader fileReader = new FileReader("text.txt")) {
            int x;
            while ((x = fileReader.read()) != -1) {
                stringBuilder.append((char) x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String text = stringBuilder.toString();
        System.out.println(text);
        System.out.println();

        String str = "if";
        Pattern pattern1 = Pattern.compile("(^|\\s)" + str + "(\\.|\\s|\\b)");
        Matcher matcher1 = pattern1.matcher(text);
        List<String> list = new ArrayList<>();
        while (matcher1.find()) {
            list.add(matcher1.group());
        }
        System.out.println("Слово - '" + str + "' найдено " + list.size() + " раз.");

        String[] arr = text.split("\\.");
        Pattern pattern2 = Pattern.compile("^[a-z].+");
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            Matcher matcher2 = pattern2.matcher(arr[i].trim());
            if (matcher2.find()) {
                count++;
            }
        }
        if (count > 0) {
            System.out.println("Количество предложений которые начинаются с маленькой буквы : " + count);
        } else {
            System.out.println("Нет предложений которые начинаются с маленькой буквы");
        }

        Pattern pattern3 = Pattern.compile("(\\w)\\1+");
        Matcher matcher3 = pattern3.matcher(text);
        if (matcher3.find()) {
            System.out.println("В тексте присутствуют буквы повторяющиеся несколько раз подряд");
        } else {
            System.out.println("В тексте отсутствуют буквы повторяющиеся несколько раз подряд");
        }
    }
}


