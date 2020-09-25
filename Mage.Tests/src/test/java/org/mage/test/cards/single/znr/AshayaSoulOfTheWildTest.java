package org.mage.test.cards.single.znr;

import mage.abilities.mana.GreenManaAbility;
import mage.constants.CardType;
import mage.constants.PhaseStep;
import mage.constants.SubType;
import mage.constants.Zone;
import org.junit.Test;
import org.mage.test.serverside.base.CardTestPlayerBase;

/**
 * @author TheElk801
 */
public class AshayaSoulOfTheWildTest extends CardTestPlayerBase {

    private static final String ashaya = "Ashaya, Soul of the Wild";
    private static final String bear = "Grizzly Bears";
    private static final String forest = "Forest";
    private static final String transformation = "Kenrith's Transformation";

    @Test
    public void testAshaya() {
        setStrictChooseMode(true);
        addCard(Zone.HAND, playerA, ashaya);
        addCard(Zone.BATTLEFIELD, playerA, forest, 5);
        addCard(Zone.BATTLEFIELD, playerA, bear);

        castSpell(1, PhaseStep.PRECOMBAT_MAIN, playerA, ashaya);

        setStopAt(1, PhaseStep.END_TURN);
        execute();

        assertAllCommandsUsed();

        assertType(ashaya, CardType.LAND, SubType.FOREST);
        assertType(ashaya, CardType.CREATURE, SubType.ELEMENTAL);
        assertAbility(playerA, ashaya, new GreenManaAbility(), true);

        assertType(bear, CardType.LAND, SubType.FOREST);
        assertType(bear, CardType.CREATURE, SubType.BEAR);
        assertAbility(playerA, bear, new GreenManaAbility(), true);
    }

    @Test
    public void testAshayaNoAbilities() {
        setStrictChooseMode(true);
        addCard(Zone.HAND, playerA, ashaya);
        addCard(Zone.HAND, playerA, transformation);
        addCard(Zone.BATTLEFIELD, playerA, forest, 7);
        addCard(Zone.BATTLEFIELD, playerA, bear);

        castSpell(1, PhaseStep.PRECOMBAT_MAIN, playerA, ashaya);
        castSpell(1, PhaseStep.POSTCOMBAT_MAIN, playerA, transformation, ashaya);

        setStopAt(1, PhaseStep.END_TURN);
        execute();

        assertAllCommandsUsed();

        assertType(ashaya, CardType.LAND, false);
        assertNotSubtype(ashaya, SubType.ELEMENTAL);
        assertNotSubtype(ashaya, SubType.FOREST);
        assertType(ashaya, CardType.CREATURE, SubType.ELK);
        assertAbility(playerA, ashaya, new GreenManaAbility(), false);

        assertType(bear, CardType.LAND, SubType.FOREST);
        assertType(bear, CardType.CREATURE, SubType.BEAR);
        assertAbility(playerA, bear, new GreenManaAbility(), true);
    }
}
