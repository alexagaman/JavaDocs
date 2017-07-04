package Zoo.Personal;

import Exceptions.AnimalManancaAnimalException;
import Exceptions.AnimalPeCaleDeDisparitieException;
import Zoo.Animale.Animal;
import Zoo.Animale.AnimalZooRar;

/**
 * Created by Alexandra.Gaman on 7/4/2017.
 */
public class IngrijitorZoo implements AngajatZoo {
    private int sumaBonus;
    private String nume;

    public void setNume(String n){
        nume = n;
    }

    public String getNume() {
        return nume;
    }

    public IngrijitorZoo() {
        sumaBonus = 0;
    }

    public int getSumaBonus(){
        return sumaBonus;
    }
    public void setSumaBonus(int s){
        sumaBonus = s;
    }
    @Override
    public void lucreaza(Animal animal) {
        System.out.println("Ingrijitorul intra in cusca animalului");
    }

    @Override
    public void calculeazaBonusSalarial() {
        sumaBonus += 3 * valoareBonusPerAnimal;
    }

    public void lucreaza(Animal animal, Object mancare) throws AnimalPeCaleDeDisparitieException, AnimalManancaAnimalException {
        lucreaza(animal);
        animal.mananca(mancare);
        if(animal instanceof AnimalZooRar && mancare == null){
            throw new AnimalPeCaleDeDisparitieException();
        }
        animal.seJoaca();
        animal.faceBaie();
        animal.doarme();
        calculeazaBonusSalarial();
    }
}
