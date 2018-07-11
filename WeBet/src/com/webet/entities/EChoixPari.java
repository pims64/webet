package com.webet.entities;

public enum EChoixPari {
    VICTOIRE_DOMICILE {
	@Override
	public Double calculGains(Rencontre rencontre, Pari pari) {
	    Double gain = rencontre.getCoteDomicile() * pari.getSommePariee();
	    return gain;
	}
    },
    NUL {
	@Override
	public Double calculGains(Rencontre rencontre, Pari pari) {
	    Double gain = rencontre.getCoteVisiteur() * pari.getSommePariee();
	    return gain;
	}
    },
    VICTOIRE_VISITEUR {
	@Override
	public Double calculGains(Rencontre rencontre, Pari pari) {
	    Double gain = rencontre.getCoteNul() * pari.getSommePariee();
	    return gain;
	}
    };

    public String getName() {
	return name();
    }

    public abstract Double calculGains(Rencontre rencontre, Pari pari);

}
