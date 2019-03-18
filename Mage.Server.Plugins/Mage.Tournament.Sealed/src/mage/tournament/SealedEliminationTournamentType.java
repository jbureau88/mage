

package mage.tournament;

import mage.game.tournament.TournamentType;

/**
 *
 * @author BetaSteward_at_googlemail.com
 */
public class SealedEliminationTournamentType extends TournamentType {

    public SealedEliminationTournamentType() {
        this.name = "Sealed Elimination";
        this.maxPlayers = 16;
        this.minPlayers = 2;
        this.numBoosters = 6;
        this.draft = false;
        this.limited = true;
        this.cubeBooster = false;
        this.elimination = true;
    }

}
