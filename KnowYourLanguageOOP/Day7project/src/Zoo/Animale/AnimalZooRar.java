package Zoo.Animale;

import Exceptions.AnimalManancaAnimalException;
import Exceptions.AnimalManancaOmException;
import Zoo.Personal.AngajatZoo;

/**
 * Created by Alexandra.Gaman on 7/4/2017.
 */
public class AnimalZooRar extends Animal {
    private String nume;
    private String numeTaraDeOrigine;

    public AnimalZooRar() {
        nume = "fara nume";
        numeTaraDeOrigine = "Nu se stie tara";
    }

    public AnimalZooRar(String n){
        nume = n;
        numeTaraDeOrigine = "Nu se stie tara de origine";
    }
    public  AnimalZooRar(String n, String t){
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
            System.out.println("AnimalZooRar mananca");
        }
    }

    @Override
    public void seJoaca() {
        System.out.println("AnimalulZooRar se joaca");
        super.doarme();
    }

    @Override
    public void faceBaie() {
        System.out.println("AnimalulZooRar face baie");
    }
}
