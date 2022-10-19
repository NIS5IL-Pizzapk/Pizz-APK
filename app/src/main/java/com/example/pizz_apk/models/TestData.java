package com.example.pizz_apk.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestData {
    public static List<PlatPropose> listePizzas = new ArrayList<PlatPropose>(Arrays.asList(
            new PlatPropose("Pizza","4 Fromages","roquefort, chèvre, mozza",14,new ArrayList<>(Arrays.asList(new Allergene("lait"),new Allergene("oeuf"))),new ArrayList<>(Arrays.asList(new Tag("Rouge")))),
            new PlatPropose("Pizza","Flamme","lardons, oignons, mozza",14,new ArrayList<>(Arrays.asList(new Allergene("lait"),new Allergene("oeuf"))),new ArrayList<>(Arrays.asList(new Tag("Blanche")))),
            new PlatPropose("Pizza","La Una Vita","mozza, roquette, jambon cru, parmesan, tomates cerises, crème balsamique, huile d'olive",14.50F,new ArrayList<>(Arrays.asList(new Allergene("lait"),new Allergene("oeuf"))),new ArrayList<>(Arrays.asList(new Tag("Rouge")))),
            new PlatPropose("Pizza","Camembert","camembert(250g), jambon cru serrano, basilic, huile d'olive, mozza",14.50F,new ArrayList<>(Arrays.asList(new Allergene("lait"),new Allergene("oeuf"))),new ArrayList<>(Arrays.asList(new Tag("Blanche")))),
            new PlatPropose("Pizza","Chorizo","chorizo, mozza",14.50F,new ArrayList<>(Arrays.asList(new Allergene("lait"),new Allergene("oeuf"))),new ArrayList<>(Arrays.asList(new Tag("Rouge"))))
            ));
}
