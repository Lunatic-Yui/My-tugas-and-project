
import java.util.ArrayList;
import java.util.Scanner;      

public class lylera {

    //untuk awalannya
    static Scanner objeknya = new Scanner(System.in);
    static ArrayList<vtuber> list_vtuber = new ArrayList<>();

    public static void pengenalan() {

        //ngelooping dengan kondisi yang sesuai sampai akhir
        while (true) {
            System.out.println("\n=== |< Bikin char dulu disini >| ===");

            //seribet ini bikinnya jir...
            System.out.print("Okay, silahkan masukin nama vtuber: ");
            String nama_vtuber = objeknya.nextLine().trim().replaceAll("\\s+", " ");
            System.out.print("Masukin hobi vtuber: ");
            String hobi_vtuber = objeknya.nextLine().trim().replaceAll("\\s+", " ");
            System.out.print("Masukin umur (angka doang yak): ");
            String umur_string = objeknya.nextLine().replaceAll("\\D+", "");
            
            // set ke 0 karena awalnya pakai string.
            int umur = 0;
            if (umur_string != "") {
                umur = Integer.parseInt(umur_string);
            }

            System.out.print("Apakah karakter vtubernya indie, agensi, atau circle? ");
            String vtuber_milih = objeknya.nextLine().trim().toLowerCase();

            vtuber vBaru = null;

            if (vtuber_milih.equals("indie")) {
                System.out.print("Rencana kamu mau stream apa: ");
                String kegiatan = objeknya.nextLine().trim();
                vBaru = new indie_Vtuber(nama_vtuber, hobi_vtuber, umur, kegiatan);      
            } 
            else if (vtuber_milih.equals("agensi")) {

                System.out.print("Nama agensimu: ");
                String agensi = objeknya.nextLine().trim();
                System.out.print("Rencana kamu mau stream apa: ");
                String kegiatan = objeknya.nextLine().trim();
                vBaru = new agent_Vtuber(nama_vtuber, hobi_vtuber, umur, agensi, kegiatan);      
            } 
            else if (vtuber_milih.equals("circle")) {

                System.out.print("Ada server discord? (ada/nggak): ");
                String jawaban = objeknya.nextLine().trim().toLowerCase();
                System.out.print("Rencana kamu mau stream apa: ");
                String kegiatan = objeknya.nextLine().trim();

                if (jawaban.equals("ada")) {
                    System.out.println("Sip, link discord bisa dipasang di bio tempat streamingmu nanti sama nama circle: ");
                    String circle_vtuber = objeknya.nextLine().trim();
                    vBaru = new circle_Vtuber(nama_vtuber, hobi_vtuber, umur, circle_vtuber, kegiatan);
                } 
                else {
                    System.out.println("Oh baru bikin? yaudah gapapa, rencana nama circle: ");
                    String circle_vtuber = objeknya.nextLine().trim();
                    vBaru = new circle_Vtuber(nama_vtuber, hobi_vtuber, umur, circle_vtuber, kegiatan);
                }
                
            } else {
                System.err.println("Gk ada pilihannya. Coba perhatikan pilihannya");
                continue; 
            }

            if (vBaru != null) {
                list_vtuber.add(vBaru);
                Database_Vtuber.simpen_dulu_bossku(list_vtuber);
                System.out.println("Mantap! " + nama_vtuber + " resmi debut.");
                break; 
            }
        }
    }

    // ini list vtuber sih
    public static void list() {

        //checking kondition jika kosong
        if (list_vtuber.isEmpty()) {
            System.out.println("Yah, datanya masih kosong bro. Bikin dulu gih!");
            return; 
        }

        System.out.println("\n === |< Daftar vtuber >| === ");
        for (int i = 0; i < list_vtuber.size(); i++) {
            System.out.println((i + 1) + ". " + list_vtuber.get(i).nama + " (Umur: " + list_vtuber.get(i).getumur() + ")");
        }
    }

    // reset database
    public static void deleteAllList() {
        System.out.print("Kamu mau reset dbnya ini? (y/n): ");
        String yakin = objeknya.nextLine().trim().toLowerCase();
        
        if (yakin.equals("y")) {
            list_vtuber.clear();
            Database_Vtuber.simpen_dulu_bossku(list_vtuber);
            System.out.println("Database berhasil di reset brok, omaygattttttttt.");
        } 
        else if (yakin.equals("n")) {
            System.out.println("Santuy, gk bakal dihapus kok");
        } else {
            System.err.println("Pilihannya gk ada");
        }
    }

    public static void cekHasil() {

        //checking
        System.out.print("Masukin nama vtuber yang mau dicek profilnya: ");
        String target = objeknya.nextLine().trim();
        boolean ketemu = false;

        for (vtuber v : list_vtuber) {
            if (v.nama.equalsIgnoreCase(target)) {
                System.out.println("\n === |< Profile vtuber >| ===");
                System.out.println("Nama : " + v.nama);
                System.out.println("Hobi : " + v.hobi);
                System.out.println("Umur : " + v.getumur());
                ketemu = true;
                break;
            }
        }
        
        //kalau misal gk ketemu
        if (!ketemu) {
            System.err.println("Ga nemu bro vtuber namanya " + target);
        }
    }

    // function untuk vtuber collab
    public static void Collab_vtuber() {
        if (list_vtuber.size() < 2) {
            System.out.println("Minimal harus ada 2 vtuber di list buat collab woi!");
            return;
        }

        System.out.print("Nama Vtuber pertama yang mau diajak: ");
        String nama1 = objeknya.nextLine().trim();
        System.out.print("Nama Vtuber kedua: ");
        String nama2 = objeknya.nextLine().trim();

        boolean ada1 = false, ada2 = false;

        // ini buat loopingan sederhana
        for (vtuber v : list_vtuber) {
            if (v.nama.equalsIgnoreCase(nama1)) ada1 = true;
            if (v.nama.equalsIgnoreCase(nama2)) ada2 = true;
        }

        if (ada1 && ada2) {
            System.out.println("\n Hi hi gess aku " + nama1 + " dengan temanku " + nama2 + " bakal collab hari ini. Kira-kira collab apa yahhhh, hemmmmmmm");
        } else {
            System.err.println("Gagal collab! Salah satu dari mereka ga ada di list.");
        }
    }

    public static void donasi() {
        System.out.print("Mau kasih donasi ke siapa nih: ");
        String target = objeknya.nextLine().trim();
        
        System.out.print("Masukin nominalnya (angka aja): Rp ");
        String uangkuuuuu_str = objeknya.nextLine().replaceAll("\\D+", "");
        
        if (uangkuuuuu_str.isEmpty()) {
            System.out.println("Nominalnya yang bener dong ah. Minimal 1000000 kek, eh salah. bukan sultan. maaf- 10000 cukup, hehe-");
            return;
        }

        for (vtuber v : list_vtuber) {
            if (v.nama.equalsIgnoreCase(target)) {

                System.out.println("\n Anjayy disawer cuuyyy!" + v.nama + " dapet donasi Rp " + uangkuuuuu_str + "!");
                System.out.println(v.nama + ": \"Wah makasih banyak ya orang baik!! Besok besok kasih lagi yakkk xD\"");
                return;
            }
        }
        System.err.println("Heee kamu halu apa gmn sih. GK ADA OEYYYYYY ");
    }

    public static void graduateVtuber() {


        System.out.print("Masukin nama vtuber graduate :< : ");
        String target = objeknya.nextLine().trim();
        
        for (int i = 0; i < list_vtuber.size(); i++) {
            if (list_vtuber.get(i).nama.equalsIgnoreCase(target)) {
                list_vtuber.remove(i);
                Database_Vtuber.simpen_dulu_bossku(list_vtuber);
                System.out.println(target + " resmi graduate... Sedih banget T_T. Kangen gk sih???");
                return;
            }
        }
        System.err.println("Gak nemu namanya bro. Coba cari lagi");
    }

public static void sekarang_oop_combine() {
        System.out.print("Masukin nama vtuber buat dengerin opening khasnya: ");
        String target = objeknya.nextLine().trim();
        boolean ketemu = false;

        for (vtuber v : list_vtuber) {
            if (v.nama.equalsIgnoreCase(target)) {
                ketemu = true;
                System.out.println("\n === |< AKTIVITAS VTUBER >| ===");
                System.out.println("1. Liat Opening Khas");
                System.out.println("2. Liat Dia Streaming");
                System.out.println("3. Dengerin Cover Lagu");
                System.out.println("4. Dengerin Lagu Original");
                System.out.print("Pilih mau suruh dia ngapain (1-4): ");
                String aksi = objeknya.nextLine().replaceAll("\\D+", "");

                if (v instanceof vtuber_activity) {
                    
                    vtuber_activity vSkill = (vtuber_activity) v;
                    
                    switch (aksi) {
                        case "1" -> v.opening(); 
                            
                        case "2"-> vSkill.streaming(); 
                            
                        case "3"-> {
                            System.out.print("Masukin judul lagu yang mau di-cover: ");
                            String judulCover = objeknya.nextLine();
                            System.out.println("");
                            vSkill.cover_lagu(judulCover);
                        }
                        case "4" -> {
                            System.out.print("Masukin judul lagu orinya: ");
                            String judulOri = objeknya.nextLine();
                            System.out.println("");
                            vSkill.lagu_vtuber(judulOri);
                        }
                        default -> System.err.println("Cuman ada 1 sampai 4 doang wok. Plis lah yak");
                    }
                } else {
                     v.opening(); 
                }
                break; 
            }
        }
        
        if (!ketemu) {
            System.err.println("Ga nemu bro vtuber namanya " + target);
        }
    }

    public static void main(String[] args) {

        Database_Vtuber.baca_filenya_bossku(list_vtuber);
        
        while (true) {
            
            //pilihan vtuber
            System.out.println("\n===============================================");
            System.out.println("       |< Mari pengenalan tentang vtuber >|     ");
            System.out.println("===============================================");
            System.out.println("1. Bikin vtuber");
            System.out.println("2. List vtuber");
            System.out.println("3. Reset database");
            System.out.println("4. Cek profile");
            System.out.println("5. Collab vtuber");
            System.out.println("6. Donasi ke vtuber :>");
            System.out.println("7. Vtuber udah graduate :<");
            System.out.println("8. Cek hasil (penggunaan oop sekarang)");
            System.out.println("9. Exit");
            System.out.println("===============================================");
            System.out.print("Silahkan pilih menu: ");
            
            String pilihan = objeknya.nextLine().replaceAll("\\D+", "");

            switch (pilihan) {
                case "1" -> pengenalan();
                case "2" -> list();
                case "3" -> deleteAllList();
                case "4" -> cekHasil();
                case "5" -> Collab_vtuber();
                case "6" -> donasi();
                case "7" -> graduateVtuber();
                case "8" -> sekarang_oop_combine();
                case "9" -> {

                    // ini menu exit
                    System.out.println("Menyimpan data ke database. terima kasihhh yayayayaya!");
                    Database_Vtuber.simpen_dulu_bossku(list_vtuber);
                    System.exit(0);
                }
                default -> System.err.println("Gk bisa lebih wok. cuman 1 sampai 9 aja");
            }
        }
    }
}