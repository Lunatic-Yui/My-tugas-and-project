/*
    Tempat database ini
*/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Database_Vtuber {

    //ini yang di private, gk bisa diubah oleh public.
    private static final String FOLDER_DB = "db";
    private static final String NAMA_FILE = "db/data_vtuber.csv";

    private static void foldernya_ada_atau_tidak() {

        //kalau gk ada, bikin dulu brok
        File folder = new File(FOLDER_DB);
        if (!folder.exists()) {
            folder.mkdir(); 
            System.out.println("Folder 'db' berhasil dibuat!");
        }
    }

    public static void simpen_dulu_bossku(ArrayList<vtuber> listVtuber) {

        //checking foldernya dulu brok
        foldernya_ada_atau_tidak(); 
        
        try (FileWriter writer = new FileWriter(NAMA_FILE)) {
            for (vtuber v : listVtuber) {

                String baris = v.nama + "," + v.hobi + "," + v.getumur() + "\n";
                writer.write(baris);
            }
            System.out.println("Data berhasil diamankan ke " + NAMA_FILE + " Mayan lah yakk");
        } catch (IOException e) {
            System.err.println("Waduh, gagal nyimpen file. Errornya sih: " + e.getMessage() + " yak. Coba benerin dulu");
        }
    }

    public static void baca_filenya_bossku(ArrayList<vtuber> listTujuan) {
        File fileDb = new File(NAMA_FILE);
        
        if (!fileDb.exists()) {
            System.out.println("File belum ada, mulai dengan list kosong.");
            return; 
        }

        try (Scanner pembacaFile = new Scanner(fileDb)) {
            while (pembacaFile.hasNextLine()) {
                String baris = pembacaFile.nextLine();
                String[] data = baris.split(",");
                
                if (data.length >= 3) {
                    String nama = data[0];
                    String hobi = data[1];
                    int umur = Integer.parseInt(data[2]);
                    
                    listTujuan.add(new indie_Vtuber(nama, hobi, umur, "Streaming"));
                }
            }
            System.out.println("Sukses!! " + listTujuan.size() + " Vtuber dari file.");
        } catch (Exception e) {
            System.err.println("Error tidak bisa membaca filenya: " + e.getMessage());
        }
    }
}