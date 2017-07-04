package Zoo.Personal;

import Zoo.Animale.Animal;
import Zoo.Animale.AnimalZooFeroce;
import Zoo.Animale.AnimalZooRar;

/**
 * Created by Alexandra.Gaman on 7/4/2017.
 */
public class VeterinarZoo implements AngajatZoo {
    private int sumaBonus;
    private String nume;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setSumaBonus(int sumaBonus) {
        this.sumaBonus = sumaBonus;
    }

    public VeterinarZoo() {
        sumaBonus = 0;
    }

    public int getSumaBonus(){
        return sumaBonus;
    }

    @Override
    public void lucreaza(Animal animal) {
        System.out.println("Veterinarul are grija de animal");
        if(animal instanceof AnimalZooFeroce){
            animal.faceBaie();
        }
        calculeazaBonusSalarial();
    }

    @Override
    public void calculeazaBonusSalarial() {
        sumaBonus += 2 * valoareBonusPerAnimal;
    }
}
