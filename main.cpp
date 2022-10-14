#include <iostream>
#include <fstream>
using namespace std;
const int DIM = 2000000;
string v[DIM];

void ScambiaNomi (string v[], int dim) {
    for (int i = 0; i < dim; ++i) {
        string Nome;
        string Cognome;
        int m = v[i].find(' ');
        Nome = v[i].substr(0, m);
        Cognome = v[i].substr(m + 1);
        v[i] = Cognome;
        v[i] += ";";
        v[i] += Nome;
    }
}

int main() {
        int dim = 20;
        while (dim <= 2000000) {
        ifstream in("Nomi_" + to_string(dim) + ".txt");
        ofstream out("Nomi_" + to_string(dim) + ".csv");

        for (int i = 0; i < dim; ++i) {
            getline(in, v[i]);
        }

        ScambiaNomi(v, dim);

        for (int i = 0; i < dim; ++i) {
            out << v[i] << endl;
        }

        dim*=10;
        in.close();
        out.close();
        }

    return 0;
    }