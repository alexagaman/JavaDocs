package Zoo.Animale;

import Exceptions.AnimalManancaAnimalException;
import Exceptions.AnimalManancaOmException;
import Zoo.Personal.AngajatZoo;

/**
 * Created by Alexandra.Gaman on 7/4/2017.
 */
public class AnimalZooFeroce extends Animal {
    private String nume;
    private String numeTaraDeOrigine;

    public AnimalZooFeroce(){
        nume = "Fara nume";
        numeTaraDeOrigine = "Nu se stie tara de origine";
    }

    public AnimalZooFeroce(String n){
        nume = n;
        numeTaraDeOrigine = "Nu se stie tara de origine";
    }
    public  AnimalZooFeroce(String n, String t){
        nume = n;
        numeTaraDeOrigine = t;
    }

    public void setNume(String n){
        nume =n;
    }

    public String getNume(){
        return nume;
    }

    public void setNumeTaraDeOrigine(String n){
        numeTaraDeOrigine =n;
    }

    public String getNumeTaraDeOrigine(){
        return numeTaraDeOrigine;
    }

    @Override
    public void mananca(Object obj) throws AnimalManancaAnimalException {
        super.mananca(obj);
        if(obj instanceof AngajatZoo){
            throw new AnimalManancaOmException();
        }
        else{
            System.out.println("AnimalZooFeroce mananca");
        }
    }

    @Override
    public void seJoaca() {
        System.out.println("AnimalZooFeroce se joaca");
        super.doarme();
    }

    @Override
    public void faceBaie() {
        System.out.println("AnimalZooFeroce face baie");
    }

    @Override
    public void doarme() {
        super.doarme();
        System.out.println("Animalul feroce vaneaza");
    }
}
