//this is latihannya

public class tugas {
    public static void main(String[] args) {
        corpoVtuber lyra = new corpoVtuber("Lyra", "Mizuchi", 20, "Komkom");
        indieVtuber lyle = new indieVtuber("Lyle", "Mizuchi", 21);

        lyle.opening();
        lyra.opening();

    }
}
abstract class vtuber {
    protected String nama, asal;
    protected int umur;
    
    public vtuber(String nama, String asal, int umur) {
        this.nama = nama;
        this.asal = asal;
        this.umur = umur;
    } 
    abstract void opening();
    public int getumur() {
        return this.umur;
    } 
    public void setUmur(int setnewUmur) {
        this.umur = setnewUmur;
    }
}

class corpoVtuber extends vtuber {
    private String agensi;

    public corpoVtuber(String nama, String asal, int umur, String agensi) {
        super(nama, asal, umur);
        this.agensi = agensi;
    }

    @Override
    void opening() {
        System.out.println("Konnichiwa minnasan, watashiwa  " + this.nama + ". I'm from " + this.agensi);
    }
}

class indieVtuber extends vtuber {
    public indieVtuber(String nama, String asal, int umur) {
        super(nama, asal, umur);
    }
    @Override
    void opening() {
        System.out.println("Nyahooo Minna san!");
    }
}