package com.gateway.data.objects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PokemonDO {
    private int id;
    private String name;
    private String image;
    private String type1;
    private String type2;
    private double height;
    private double weight;
    private boolean caught;
    private int levelEvolution;
    private String conditionEvolution;
    private List<CapacityDO> capacities;
    private List<TalentDO> talents;
    private StatisticsDO statistics;
    private List<PokemonDO> nextEvolutions;
    private List<PokemonDO> previousEvolutions;
    private List<WeaknessDO> weaknesses;
}
