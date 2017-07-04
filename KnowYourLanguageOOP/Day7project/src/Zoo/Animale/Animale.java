package Zoo.Animale;

import Exceptions.AnimalManancaAnimalException;

/**
 * Created by Alexandra.Gaman on 7/4/2017.
 */
public interface Animale {
    public void mananca(Object obj) throws AnimalManancaAnimalException;
    public void seJoaca();
    public void faceBaie();
}
