package Zoo.Personal;

import Zoo.Animale.Animal;

/**
 * Created by Alexandra.Gaman on 7/4/2017.
 */
public interface AngajatZoo {
    Integer valoareBonusPerAnimal = 50;
    void lucreaza(Animal animal);
    void calculeazaBonusSalarial();
}
