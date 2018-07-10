package com.webet.entities;

public enum EChoixPari {
    VICTOIRE_DOMICILE {
	@Override
	public void calculGains(Rencontre rencontre, Pari pari) {
	    Double gain = rencontre.getCoteDomicile() * pari.getSommePariee();
	    pari.setGain(gain);
	}
    },
    NUL {
	@Override
	public void calculGains(Rencontre rencontre, Pari pari) {
	    Double gain = rencontre.getCoteVisiteur() * pari.getSommePariee();
	    pari.setGain(gain);
	}
    },
    VICTOIRE_VISITEUR {
	@Override
	public void calculGains(Rencontre rencontre, Pari pari) {
	    Double gain = rencontre.getCoteNul() * pari.getSommePariee();
	    pari.setGain(gain);
	}
    };

    public String getName() {
	return name();
    }

    public abstract void calculGains(Rencontre rencontre, Pari pari);

}
