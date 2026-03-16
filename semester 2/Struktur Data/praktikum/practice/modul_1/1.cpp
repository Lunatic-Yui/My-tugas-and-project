#include <iostream>
#include <string>
#define MAX 100
using namespace std;

class Karakter {
    protected:
    string nama;
    char tier;
    int hp, attackPower;

    public: 
    //constructor
    Karakter(string nama, char tier, int hp, int attackPower) {
        this->nama = nama;
        this->tier = tier;
        this->hp = hp;
        this->attackPower = attackPower;
    }

    void setTier(char newTier) {
        this->tier = newTier;
    }
    char getTier() {
        return tier;
    }
    string getName() {
        return nama;
    }

    virtual void displayInfo() {
        if (nama != "") {
            cout << "Name: " << nama << " | Tier: " << tier << " | HP: " << hp << " | AP: " << attackPower << " | ";
        }
    }

    virtual void useSkill() = 0;
};

//stamina >= 20, stamina - 20;
class Warrior : public Karakter{
    private: int stamina;

    public: 
    
    Warrior(string nama, char tier, int hp, int attackPower, int stamina) : Karakter(nama, tier, hp, attackPower) {
        this->stamina = stamina;
    }

    void displayInfo() override {
        Karakter::displayInfo();
        cout << "stamina kamu: " << stamina << endl;
    }

    void useSkill() override {
        if (stamina >= 20) {
            stamina -= 20;
            cout << "Karakter" << nama << "menebas musuh dengan pedang!" << endl;
        } else {
            cout << "Stamina" << nama << "tidak cukup" << endl;
        }
    }
};

//mage >= 30, mana - 30;
class Mage : public Karakter{
    private: int mana;

    public:
    Mage(string nama, char tier, int hp, int attackPower, int mana) : Karakter(nama, tier, hp, attackPower) {
        this->mana = mana;
    }

    void displayInfo() override {
        Karakter::displayInfo();
        cout << "mana kamu: " << mana << endl;
    }

    void useSkill() override {
        if (mana >= 30) {
            mana -= 30;
            cout << "karakter" << nama << " telah menggunakan elemental burst!" << endl;
        } else {
            cout << "karakter" << nama << "mana tidak cukup" << endl;
        }
    }
};

Karakter* daftarKarakter[100]; int totalKarakter = 0;

void createKarakter() {
    string name; char tier;
    int hp, attackPower, type;
    cout << "Masukkan nama: ";
    cin.ignore();
    getline(cin, name);
    for (int i = 0; i < totalKarakter; i++) {
        if (daftarKarakter[i]->getName() == name) {
            cout << "Nama karakter sudah ada!" << endl;
            return;
        }
    }
    cout << "Masukkan tier: ";
    cin >> tier;
    if (tier != 'A' && tier != 'B' && tier != 'C' && tier != 'D' && tier != 'E') {
        //gk pake or?
        cout << "Tier tidak valid!" << endl;
        return;
    }
    cout << "Masukkan HP: ";
    cin >> hp;
    cout << "Masukkan attack power: ";
    cin >> attackPower;
    cout << "Masukkan tipe (1: Warrior, 2: Mage): ";
    cin >> type;
    if (type == 1) {
        int stamina;
        cout << "Masukkan stamina: ";
        cin >> stamina;
        daftarKarakter[totalKarakter] = new Warrior(name, tier, hp, attackPower, stamina);
        totalKarakter++;
    } else if (type == 2) {
        int mana;    
        cout << "Masukkan mana: ";
        cin >> mana;
        daftarKarakter[totalKarakter] = new Mage(name, tier, hp, attackPower, mana);
        totalKarakter++;
    } else {
        cout << "Tipe tidak valid!" << endl;
    }
}

void displayKarakter() {
    if (totalKarakter == 0) {
        cout << "Belum ada karakter!" << endl;
    } else {
        cout << "==== Daftar Karakter ====" << endl;
        for (int i = 0; i < totalKarakter; i++) {
            cout << i + 1 << ". ";
            daftarKarakter[i]->displayInfo();
        }
    }
}

void promote() {
    string name;
    cout << "Masukkan nama karakter: ";
    cin.ignore();
    getline(cin, name);
    bool found = false;
    for (int i = 0; i < totalKarakter; i++) {
        if (daftarKarakter[i]->getName() == name) {
            found = true;
            char curTier;
            curTier = daftarKarakter[i]->getTier();
            if (curTier == 'A') {
                cout << "Karakter " << name << " sudah tier A!" << endl;
                break;
            }
            daftarKarakter[i]->setTier(curTier - 1); // Character Arithmetic (kalau minus naik urutan B -> A, sedangkan plus turun urutan A-> B) 
            cout << "Karakter " << name << " berhasil dipromote ke tier " << daftarKarakter[i]->getTier() << endl;
            break;
        }
    }
    if (!found) {
        cout << "Karakter " << name << " tidak ditemukan!" << endl;
    }
}

void deleteKarakter() {
    string name;
    cout << "Masukkan nama karakter: ";
    cin.ignore();
    getline(cin, name);
    bool found = false;
    for (int i = 0; i < totalKarakter; i++) {
        if (daftarKarakter[i]->getName() == name) {
            found = true;
            delete daftarKarakter[i];
            for (int j = i; j < totalKarakter - 1; j++) {
                daftarKarakter[j] = daftarKarakter[j + 1];
            }
            totalKarakter--;
            cout << "Karakter " << name << " berhasil dihapus!" << endl;
            break;
        }
    }
    if (!found) {
        cout << "Karakter " << name << " tidak ditemukan!" << endl;
    }
}

void trySkill() {
    string name;
    cout << "Masukkan nama karakter: ";
    cin.ignore();
    getline(cin, name);
    bool found = false;
    for (int i = 0; i < totalKarakter; i++) {
        if (daftarKarakter[i]->getName() == name) {
            found = true;
            daftarKarakter[i]->useSkill();
            break;
        }
    }
    if (!found) {
        cout << "Karakter " << name << " tidak ditemukan!" << endl;
    }
}
void menu() {
    cout << "==== Donggu's Game ====" << endl;
    cout << "1. Create Karakter" << endl;
    cout << "2. Cek Daftar Karakter" << endl;
    cout << "3. Promote Karakter" << endl;
    cout << "4. Hapus Karakter" << endl;
    cout << "5. Simulasi Skill " << endl;
    cout << "6. Keluar" << endl;
}

int main() {
  while (true) {
    menu();
    int choice;
    cout << "Masukkan pilihan: ";
    cin >> choice;
    switch (choice) {
        case 1:
            system("cls");
            createKarakter();
            break;
        case 2:
            system("cls");
            displayKarakter();
            break;
        case 3:
            system("cls");
            promote();
            break;
        case 4:
            system("cls");
            deleteKarakter();
            break;
        case 5:
            system("cls");
            trySkill();
            break;
        case 6:
            system("cls");
            cout << "Terima kasih sudah bermain!" << endl;
            return 0;
        default:
            break;
    }
  }
}