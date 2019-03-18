

package mage.game.permanent.token;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.MageInt;

/**
 *
 * @author spjspj
 */
public final class TreefolkShamanToken extends TokenImpl {

    public TreefolkShamanToken() {
        super("Treefolk Shaman", "2/5 green Treefolk Shaman creature token");
        cardType.add(CardType.CREATURE);
        color.setGreen(true);
        subtype.add(SubType.TREEFOLK);
        subtype.add(SubType.SHAMAN);
        power = new MageInt(2);
        toughness = new MageInt(5);
    }

    public TreefolkShamanToken(final TreefolkShamanToken token) {
        super(token);
    }

    public TreefolkShamanToken copy() {
        return new TreefolkShamanToken(this);
    }
}
