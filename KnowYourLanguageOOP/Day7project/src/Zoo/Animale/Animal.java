package Zoo.Animale;

import Exceptions.AnimalManancaAnimalException;

/**
 * Created by Alexandra.Gaman on 7/4/2017.
 */
public abstract class Animal implements Animale{

    public Animal(){
        System.out.println("Animal nou");
    }
    public void doarme(){
        System.out.println("Animalul doarme");
    }
    public void mananca(Object obj) throws AnimalManancaAnimalException {
        if(obj instanceof Animal){
            throw new AnimalManancaAnimalException();
        }
    }

}
