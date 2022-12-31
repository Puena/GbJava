import helpers.*;

import java.util.*;

public class Homework6 {
    public static void Task() {
        NotebookStore notebookStore = new NotebookStore(new Notebook[]{
                new Notebook("Acer", "Extensa EX215-22", Cpu.AMD, 16, 500, "Linux", "black"),
                new Notebook("Acer", "Aspire 3 A315-57G", Cpu.INTEL, 8, 1000, "Windows", "white"),
                new Notebook("Acer", "Swift 3 SF316-51", Cpu.INTEL, 8, 256, "Linux", "black"),
                new Notebook("Lenovo", "IdeaPad 3-14", Cpu.AMD, 4, 256, "Windows", "gray"),
                new Notebook("Lenovo", "IdeaPad 5 Pro 16", Cpu.INTEL, 16, 500, "Windows", "black"),
                new Notebook("Lenovo", "IdeaPad Gaming 3 15IHU6", Cpu.INTEL, 16, 1500, "Windows", "red"),
                new Notebook("HP", "14s-dq", Cpu.INTEL, 8, 500, "Windows", "white"),
                new Notebook("HP", "255 G8", Cpu.INTEL, 16, 1000, "Windows", "black"),
                new Notebook("HP", "Pavilion Gaming 17-cd", Cpu.AMD, 16, 1500, "Linux", "black")
        });
        notebookStore.addNotebook(new Notebook("Apple", "Macbook Air", Cpu.INTEL, 8, 256, "MacOs", "silver"));

        Set<Notebook> result = notebookStore.createRequest();

        for (Notebook note: result) {
            System.out.println(note);
        }
    }
    public static class Notebook {
        private final int ram;
        private final int hdVolume;
        private final String operationSystem;
        private final String brand;
        private final String model;
        private final Cpu cpu;
        private final String color;

        public Notebook(String brand, String model, Cpu cpu, int ram, int hdVolume, String operationSystem, String color) {
            this.ram = ram;
            this.hdVolume = hdVolume;
            this.operationSystem = operationSystem;
            this.brand = brand;
            this.model = model;
            this.cpu = cpu;
            this.color = color;
        }

        public int getRam() {
            return ram;
        }

        public int getHdVolume() {
            return hdVolume;
        }

        public String getOperationSystem() {
            return operationSystem;
        }

        public String getBrand() {
            return brand;
        }

        public String getModel() {
            return model;
        }

        public Cpu getCpu() {
            return cpu;
        }

        public String getColor() {
            return color;
        }

        @Override
        public String toString() {
            return "Notebook{" +
                    "brand='" + brand + '\'' +
                    ", model='" + model + '\'' +
                    ", cpu=" + cpu +
                    ", ram=" + ram +
                    ", hdVolume=" + hdVolume +
                    ", operationSystem='" + operationSystem + '\'' +
                    ", color='" + color + '\'' +
                    '}';
        }
    }
    public enum Cpu{
        AMD , INTEL
    }

    public static class NotebookStore {
        Set<Notebook> notebooksOnStock;
        Map<Integer, Helper> optionsMap;
        {
            optionsMap = new HashMap<>();
            optionsMap.put(1, new GetRam());
            optionsMap.put(2, new GetHddVolume());
            optionsMap.put(3, new GetOs());
            optionsMap.put(4, new GetColor());
        }

        public NotebookStore(Notebook[] notebooks) {
            notebooksOnStock = new HashSet<>(Arrays.asList(notebooks));
        }
        public void addNotebook(Notebook notebook){
            notebooksOnStock.add(notebook);
        }
        private Set<Notebook> findByOptions(Notebook protoNote){
            int ram = protoNote.getRam();
            int hddVolume = protoNote.getHdVolume();
            String os = protoNote.getOperationSystem();
            String color = protoNote.getColor();
            Set<Notebook> result = new HashSet<>();
            for (Notebook notebook:notebooksOnStock)
                if ((ram <= notebook.getRam()) && (hddVolume <= notebook.getHdVolume())
                        && ((os.equals("___")) || (os.equals(notebook.getOperationSystem())))
                        && ((color.equals("___")) || (color.equals(notebook.getColor()))))
                    result.add(notebook);

            return result;
        }
        public Set<Notebook> createRequest(){
            int ram = 0;
            int hdVolume = 0;
            String os = "___";
            String color = "___";
            int option = 0;
            Scanner scanner = new Scanner(System.in);
            while(option != 5) {
                System.out.println("\n\n\n\n\n\n\n\n\n\n");
                System.out.printf("""
                       Введите цифру, соответствующую необходимому критерию поиска, или "5" - для поиска: \s
                       1 - ОЗУ \s
                       2 - Объем ЖД \s
                       3 - Операционная система \s
                       4 - Цвет \s
                       5 - Поиск\s
                    Текущие критерии поиска: ОЗУ(Гб): %d; HDD(Гб): %d; OS: %s; Цвет: %s
                    """, ram, hdVolume, os, color);
                if (scanner.hasNext())
                    option = scanner.nextInt();
                if (option == 1)
                    ram = (int)optionsMap.get(option).getHelper();
                else if (option == 2)
                    hdVolume = (int)optionsMap.get(option).getHelper();
                else  if (option == 3)
                    os = (String)optionsMap.get(option).getHelper();
                else if (option == 4)
                    color = (String)optionsMap.get(option).getHelper();

            }

            return findByOptions(new Notebook("","", null, ram, hdVolume, os, color));
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (Notebook notebook:notebooksOnStock) {
                stringBuilder.append(notebook);
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }
    }
}
