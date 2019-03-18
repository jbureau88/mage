
package mage.cards.c;

import java.util.UUID;
import mage.Mana;
import mage.abilities.Ability;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.mana.ColorlessManaAbility;
import mage.abilities.mana.SimpleManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Zone;

/**
 *
 * @author Plopman
 */
public final class CrystalQuarry extends CardImpl {

    public CrystalQuarry(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.LAND},"");

        // {tap}: Add {C}.
        this.addAbility(new ColorlessManaAbility());
        // {5}, {tap}: Add {W}{U}{B}{R}{G}.
        Ability ability = new SimpleManaAbility(Zone.BATTLEFIELD, new Mana(1, 1, 1, 1, 1, 0, 0, 0), new ManaCostsImpl("{5}"));
        ability.addCost(new TapSourceCost());
        this.addAbility(ability);
    }

    public CrystalQuarry(final CrystalQuarry card) {
        super(card);
    }

    @Override
    public CrystalQuarry copy() {
        return new CrystalQuarry(this);
    }
}
