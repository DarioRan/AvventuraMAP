/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure.type;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
/**
 * Classe che rappresenta un comando.
 */
public class Command {
    /**
     * Enumerazione che rappresenta il tipo di comando.
     */
    private final CommandType type;
    /**
     * Nome del comando.
     */
    private final String name;
    /**
     * Set di alias del comando.
     */
    private Set<String> alias;
    /**
     * Costruttore della classe.
     * @param typeParam tipo del comando
     * @param nameParam nome del comando
     */
    public Command(final CommandType typeParam,final String nameParam) {
        this.type = typeParam;
        this.name = nameParam;
    }

    /**
     * metodo che restituisce il nome del comando.
     * @return name nome del comando
     */
    public String getName() {
        return name;
    }
    /**
     * metodo che restituisce il set di alias del comando.
     * @return alias set di alias del comando
     */
    public Set<String> getAlias() {
        return alias;
    }
    /**
     * metodo che setta il set di alias del comando.
     * @param alias set di alias del comando
     */
    public void setAlias(final String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }
    /**
     * metodo che restituisce il tipo del comando.
     * @return type tipo del comando
     */
    public CommandType getType() {
        return type;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Command other = (Command) obj;
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

}
