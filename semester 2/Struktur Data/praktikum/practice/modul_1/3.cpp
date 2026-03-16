#include <iostream>
#include <string>
using namespace std;

class pbc {
    protected:
    string nama;
    int tt, koin, exp;

    public:
    pbc(string nama, int tt) {
        this->nama = nama;
        this->tt = tt;
        this->koin = koin = 0;
        this->exp = exp = 0;
    }

    virtual ~pbc();

    string getNama() {
        return nama;
    }

    virtual void hunt(int baseKoin, int baseExp, int jumlahParty) = 0;
    virtual void info() {
        cout << "Nama: " << nama << " | Koin: " << koin << " | EXP: " << exp << endl;
    }
};

class soloPlayer : public pbc {
    public:
    soloPlayer(string nama, int tt) : pbc(nama, tt) {}
        void hunt (int baseKoin, int baseExp, int jumlahParty) override {
            koin += baseKoin;

            exp += baseExp * (1 + tt);
        }

        void info() override {
            cout << "[tipe: solo]";

            pbc::info();
        }
};

int main() {
    int n;

    cout << "erpg simulation game" << endl;
    cout << "masukkan jumlah player yg main: ";

    if(!(cin >> n)) return 0;


}