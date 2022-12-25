package day_1;

public class ResourceSharer {
    public static void main(String[] args) {
        ElfPack elfPack = new ElfPack("./day_1/input.txt");

        System.out.println("The best packed Elf had " + elfPack.computeSumTopCaloryCount(1) + " calories");
        System.out.println("The 3 best packed Elves had " + elfPack.computeSumTopCaloryCount(3) + " calories in total");
    }


}
