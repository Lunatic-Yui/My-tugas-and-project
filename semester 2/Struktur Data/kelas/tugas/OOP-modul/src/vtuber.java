/*
    Ini adalah file khusus untuk menyimpan oop
*/

abstract class vtuber {

    //setup awalnya
    protected String nama, hobi, kegiatan_streaming;
    protected int umur;
    
    public vtuber(String nama, String hobi, int umur, String kegiatan_streaming) {
        this.nama = nama;
        this.hobi = hobi;
        this.umur = umur;
        this.kegiatan_streaming = kegiatan_streaming;
    } 

    abstract void opening();
    
    public int getumur() {
        return this.umur;
    } 
    public void setUmur(int setnewUmur) {
        this.umur = setnewUmur;
    }
}

interface vtuber_activity {
    void lagu_vtuber(String lagu);
    void cover_lagu(String lagu_cover);
    void streaming();
}

class agent_Vtuber extends vtuber implements vtuber_activity {

    // Nggak pake private biar bisa diakses Database
    String agensi;

    public agent_Vtuber(String nama, String hobi, int umur, String agensi, String kegiatan_streaming) {
        super(nama, hobi, umur, kegiatan_streaming);
        this.agensi = agensi;
    }

    @Override
    void opening() {
        System.out.println("Konnichiwa minnasan, watashiwa " + this.nama + ". I'm from " + this.agensi);
    }

    @Override
    public void lagu_vtuber(String lagunya) {
        System.out.println("Hi gesss. Aku lagi cover lagu " + lagunya + " dan kalian bisa dengerin di youtube yoww");
    }

    @Override
    public void streaming() {
        System.out.println("Hallo halo semuanya. hari ini kita streaming " + kegiatan_streaming + " yak.");
    }
    @Override
    public void cover_lagu(String covernya) {
        System.out.println("Halo semuaaa. lagu " + covernya + " coveran lagi release nihh. bisa di check yakk");
    }
}

class indie_Vtuber extends vtuber implements vtuber_activity {

    public indie_Vtuber(String nama, String hobi, int umur, String kegiatan_streaming) {
        super(nama, hobi, umur, kegiatan_streaming);
    }

    @Override
    void opening() {
        System.out.println("Nyahooo Minna san!");
    }
    @Override
    public void streaming() {
        System.out.println("Hi semua. Kali ini aku stream " + kegiatan_streaming + " yang akan menemani hari-hari kalian disaat sibuk-sibuknya");
    }
    @Override
    public void lagu_vtuber(String lagunya) {
        System.out.println("Ini lagu bikinanku ges " + lagunya + "bisa kalian check yak di youtube!");
    }
    @Override
    public void cover_lagu(String covernya) {
        System.out.println("Hi hi. Ini lagu cover yang judulnya " + covernya + ". Semoga bisa kalian nikmatin");
    }
}

class circle_Vtuber extends vtuber  implements vtuber_activity{ 
    
    String circle;

    public circle_Vtuber(String nama, String hobi, int umur, String circle, String kegiatan_streaming) {
        super(nama, hobi, umur, kegiatan_streaming);
        this.circle = circle;
    }

    @Override
    void opening() {
        System.out.println("Hi hi semua. Kembali lagi dengan aku " + this.nama + " dan kalian bisa join discord aku dan temen-temenku yak di " + this.circle + " discord");
    }
    @Override
    public void streaming() {
        System.out.println("Halooo ges. Aku dan " + circle + " Akan stream " + kegiatan_streaming + " bisa di check di pov mereka ya gess.");
    }
    @Override
    public void cover_lagu(String covernya) {
        System.out.println("hi gesss. Lagu " + covernya + " coveran baru nichh. Dengerin gk sihhh??");
    }
    @Override
    public void lagu_vtuber(String lagu_buatan) {
        System.out.println("Yow. Baru release nih " + lagu_buatan + " siapa tau kan bisa menemani hari kalian");
    }
}