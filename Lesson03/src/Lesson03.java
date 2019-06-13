import java.util.*;

public class Lesson03 {
    public static void main(String[] args) {

//      ЗАДАНИЕ 1
        Random random = new Random();
        HashMap<String, Integer> hm = new HashMap<>();
        List list = new ArrayList();
        list.add("Мурзик");
        list.add("Барсик");
        list.add("Шарик");
        list.add("Васька");
        list.add("Мурка");
        list.add("Тузик");

        for (int i = 0; i < 20; i++){
            Object str = list.get(random.nextInt(list.size()));
            System.out.print(str + " ");

            Integer res = hm.get(str);
            hm.put((String) str, res == null ? 1 : res + 1);
        }
        System.out.println("\n\n" + hm);

//      ЗАДАНИЕ 2
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Иванов", "8-925-324-41-78");
        phoneBook.add("Иванов", "8-916-304-63-92");
        phoneBook.add("Сидоров", "8-926-126-32-79");
        phoneBook.add("Пупкин", "8-925-619-36-60");
        phoneBook.add("Петров", "8-909-704-01-75");
        phoneBook.add("Жуков", "8-905-300-98-68");
        phoneBook.add("Жуков", "8-915-104-11-00");
        phoneBook.add("Кузьмин", "8-909-124-11-68");
        phoneBook.add("Кузькин", "8-925-791-55-42");

        System.out.println("Иванов: " + phoneBook.get("Иванов"));
        System.out.println("Сидоров: " + phoneBook.get("Сидоров"));
        System.out.println("Пупкин: " + phoneBook.get("Пупкин"));
        System.out.println("Петров: " + phoneBook.get("Петров"));
        System.out.println("Жуков: " + phoneBook.get("Жуков"));
        System.out.println("Кузьмин: " + phoneBook.get("Кузьмин"));
        System.out.println("Кузькин: " + phoneBook.get("Кузькин"));
        System.out.println("Васюткин: " + phoneBook.get("Васюткин"));
    }
}

class PhoneBook{
    private HashMap<String, HashSet<String>> hm;

    public PhoneBook() {
        hm = new HashMap<>();
    }

    public void add(String name, String phone) {
        HashSet<String> phones = hm.get(name);
        if (phones == null){
            phones = new HashSet<>();
        }
        phones.add(phone);
        hm.put(name,phones);
    }

    public HashSet<String> get(String name) {
        HashSet<String> phones = hm.get(name);
        if (phones == null){
            phones = new HashSet<>();
            phones.add("Нет такой записи в телефонной книге!");
        }

        return phones;
    }
}