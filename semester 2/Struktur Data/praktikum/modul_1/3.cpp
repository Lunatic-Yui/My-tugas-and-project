#include <iostream>
#include <string>
#define MAX 100
using namespace std;

class Sesi {
protected:
    int nomorSesi;
    string namaPelanggan;
    int durasiJam;
    bool sudahSelesai;

public:
    Sesi(int noSesi, string nama, int durasi) {
        nomorSesi = noSesi;
        namaPelanggan = nama;
        durasiJam = durasi;
        sudahSelesai = false; 
    }

    virtual ~Sesi() {}

    int getNomor() { return nomorSesi; }
    string getNama() { return namaPelanggan; }
    int getDurasi() { return durasiJam; }
    bool getStatus() { return sudahSelesai; }

    void setNama(string n) { namaPelanggan = n; }
    void setDurasi(int d) { durasiJam = d; }
    void setSelesai() { sudahSelesai = true; }

    virtual double hitungBiaya() = 0; 

    virtual void tampilkanInfo() {
        cout << "No: " << nomorSesi << " | Nama: " << namaPelanggan 
             << " | Durasi: " << durasiJam << " Jam | Status: " 
             << (sudahSelesai ? "Selesai" : "Berlangsung");
    }
};

class SesiReguler : public Sesi {
private: 
    int nomorPS;
    double hargaPerJam;

public:
    SesiReguler(int noSesi, string nama, int durasi, int noPS, double harga) 
        : Sesi(noSesi, nama, durasi) {
        nomorPS = noPS;
        hargaPerJam = harga;
    }

    void setNomorPS(int n) { nomorPS = n; }
    int getNomorPS() { return nomorPS; }

    double hitungBiaya() override {
        return hargaPerJam * durasiJam;
    }

    void tampilkanInfo() override {
        cout << "[Regular] ";
        Sesi::tampilkanInfo();
        cout << " | No PS: " << nomorPS << " | Biaya: Rp" << hitungBiaya() << endl;
    }
};


class SesiVIP : public Sesi {
private:
    double hargaPerJam;
    double biayaRuangan;

public:
    SesiVIP(int noSesi, string nama, int durasi, double harga, double ruangan) 
        : Sesi(noSesi, nama, durasi) {
        hargaPerJam = harga;
        biayaRuangan = ruangan;
    }

    void setBiayaRuangan(double r) { biayaRuangan = r; }
    double getBiayaRuangan() { return biayaRuangan; }

    double hitungBiaya() override {
        return (hargaPerJam * durasiJam) + biayaRuangan;
    }

    void tampilkanInfo() override {
        cout << "[VIP]     ";
        Sesi::tampilkanInfo(); 
        cout << " | Ruang Privat | Biaya: Rp" << hitungBiaya() << endl;
    }
};

Sesi* daftarSesi[MAX];
int total = 0;
int nomorUrut = 1;

void tambahSesi() {
    string nama;
    int durasi, tipe;
    
    cout << "\n--- Tambah Sesi ---\n";
    cout << "Nama Pelanggan: ";
    cin.ignore();
    getline(cin, nama);
    cout << "Durasi (Jam): ";
    cin >> durasi;
    
    cout << "Pilih Tipe Sesi: \n1. Regular \n2. VIP \nPilih: ";
    cin >> tipe;

    if (tipe == 1) {
        int noPS;
        cout << "Pilih Nomor PS: ";
        cin >> noPS;
        daftarSesi[total] = new SesiReguler(nomorUrut, nama, durasi, noPS, 15000);
        total++; nomorUrut++;
        cout << ">> Sesi Regular berhasil didaftarkan!\n";
    } 
    else if (tipe == 2) {
        daftarSesi[total] = new SesiVIP(nomorUrut, nama, durasi, 15000, 10000);
        total++; nomorUrut++;
        cout << ">> Sesi VIP berhasil didaftarkan!\n";
    } 
    else {
        cout << ">> Pilihan tidak valid! Sesi gagal dibuat.\n";
    }
}

void tampilSemuaSesi() {
    cout << "\n--- Daftar Semua Sesi ---\n";
    if (total == 0) {
        cout << "Belum ada sesi yang terdaftar.\n";
        return;
    }
    for (int i = 0; i < total; i++) {
        daftarSesi[i]->tampilkanInfo();
    }
}

void selesaikanSesi() {
    int cariNomor;
    cout << "\n--- Tandai Sesi Selesai ---\n";
    if (total == 0) {
        cout << "Belum ada sesi!\n";
        return;
    }
    cout << "Masukkan Nomor Sesi yang sudah selesai: ";
    cin >> cariNomor;

    bool ketemu = false;
    for (int i = 0; i < total; i++) {
        if (daftarSesi[i]->getNomor() == cariNomor) {
            daftarSesi[i]->setSelesai();
            cout << ">> Sesi milik " << daftarSesi[i]->getNama() << " berhasil ditandai selesai!\n";
            ketemu = true;
            break;
        }
    }
    if (!ketemu) cout << ">> Nomor Sesi tidak ditemukan!\n";
}

void hapusSesi() {
    int cariNomor;
    cout << "\n--- Hapus Sesi ---\n";
    if (total == 0) {
        cout << "Belum ada sesi!\n";
        return;
    }
    cout << "Masukkan Nomor Sesi yang akan dihapus: ";
    cin >> cariNomor;

    bool ketemu = false;
    for (int i = 0; i < total; i++) {
        if (daftarSesi[i]->getNomor() == cariNomor) {
            if (!daftarSesi[i]->getStatus()) {
                cout << ">> Sesi ini belum selesai! Yakin ingin menghapus? (y/n): ";
                char konfirmasi; cin >> konfirmasi;
                if (konfirmasi != 'y' && konfirmasi != 'Y') {
                    cout << ">> Penghapusan dibatalkan.\n";
                    return;
                }
            }

            delete daftarSesi[i]; 
            
            for (int j = i; j < total - 1; j++) {
                daftarSesi[j] = daftarSesi[j + 1];
            }
            total--;
            cout << ">> Sesi berhasil dihapus!\n";
            ketemu = true;
            break;
        }
    }
    if (!ketemu) cout << ">> Nomor Sesi tidak ditemukan!\n";
}

void totalPendapatan() {
    double pendapatan = 0;
    cout << "\n--- Total Pendapatan ---\n";
    for (int i = 0; i < total; i++) {
        pendapatan += daftarSesi[i]->hitungBiaya();
    }
    cout << "Total Potensi Pendapatan: Rp" << pendapatan << endl;
}

int main() {
    int pilihan;
    while (true) {
        cout << "\n==================================\n";
        cout << "        PS RENTAL GACOER          \n";
        cout << "==================================\n";
        cout << "1. Daftarkan Sesi Baru\n";
        cout << "2. Lihat Semua Sesi\n";
        cout << "3. Tandai Sesi Selesai\n";
        cout << "4. Hapus Sesi\n";
        cout << "5. Total Pendapatan\n";
        cout << "6. Keluar\n";
        cout << "Pilih: ";
        
        if (!(cin >> pilihan)) {
            cin.clear();
            cin.ignore(10000, '\n');
            pilihan = 0;
        }

        switch (pilihan) {
            case 1:
                tambahSesi();
                break;
            case 2:
                tampilSemuaSesi();
                break;
            case 3:
                selesaikanSesi();
                break;
            case 4:
                hapusSesi();
                break;
            case 5:
                totalPendapatan();
                break;
            case 6:
                cout << "\nMembersihkan memori..." << endl;
                for (int i = 0; i < total; i++) {
                    delete daftarSesi[i];
                }
                cout << "Selesai semua! Terima kasih." << endl;
                return 0;
            default:
                cout << ">> Pilihan menu tidak valid!" << endl;
                break;
        }
    }
}