/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.cards.p;

import java.util.UUID;
import mage.MageInt;
import mage.MageObject;
import mage.abilities.Ability;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.effects.OneShotEffect;
import mage.cards.*;
import mage.cards.repository.CardRepository;
import mage.choices.Choice;
import mage.choices.ChoiceImpl;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.game.Game;
import mage.players.Player;
import mage.target.TargetPlayer;

/**
 *
 * @author BetaSteward_at_googlemail.com & L_J
 */
public class PetraSphinx extends CardImpl {

    public PetraSphinx(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{W}{W}{W}");
        this.subtype.add(SubType.SPHINX);

        this.power = new MageInt(3);
        this.toughness = new MageInt(4);

        // {tap}: Target player chooses a card name, then reveals the top card of his or her library. If that card has the chosen name, that player puts it into his or her hand. If it doesn't, the player puts it into his or her graveyard.
        Ability ability = new SimpleActivatedAbility(Zone.BATTLEFIELD, new PetraSphinxEffect(), new TapSourceCost());
        ability.addTarget(new TargetPlayer());
        this.addAbility(ability);
    }

    public PetraSphinx(final PetraSphinx card) {
        super(card);
    }

    @Override
    public PetraSphinx copy() {
        return new PetraSphinx(this);
    }

}

class PetraSphinxEffect extends OneShotEffect {

    public PetraSphinxEffect() {
        super(Outcome.DrawCard);
        staticText = "Target player chooses a card name, then reveals the top card of his or her library. If that card has the chosen name, that player puts it into his or her hand. If it doesn't, the player puts it into his or her graveyard";
    }

    public PetraSphinxEffect(final PetraSphinxEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        MageObject sourceObject = source.getSourceObject(game);
        Player controller = game.getPlayer(source.getControllerId());
        Player player = game.getPlayer(targetPointer.getFirst(game, source));
        if (controller != null && sourceObject != null && player != null) {


            //~ Players:
            //~ for (UUID playerId : game.getState().getPlayersInRange(controller.getId(), game)) {
                //~ Player player = game.getPlayer(playerId);
            //~ if (player != null) {
            if (player.getLibrary().hasCards()) {
                Choice cardChoice = new ChoiceImpl();
                cardChoice.setChoices(CardRepository.instance.getNames());
                //~ cardChoice.clearChoice();
                cardChoice.setMessage("Name a card");
                while (!player.choose(Outcome.DrawCard, cardChoice, game)) {
                    if (!player.canRespond()) {
                        return false;
                    }
                }
                String cardName = cardChoice.getChoice();
                game.informPlayers(sourceObject.getLogName() + ", player: " + player.getLogName() + ", named: [" + cardName + ']');
                Card card = player.getLibrary().removeFromTop(game);
                if (card != null) {
                    Cards cards = new CardsImpl(card);
                    player.revealCards(sourceObject.getIdName(), cards, game);
                    if (card.getName().equals(cardName)) {
                        player.moveCards(cards, Zone.HAND, source, game);
                    } else {
                        player.moveCards(cards, Zone.GRAVEYARD, source, game);
                            //~ player.damage(2, source.getSourceId(), game, false, true);
                            //~ player.putCardsOnBottomOfLibrary(cards, game, source, false);
                            //~ }
                        //~ }
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public PetraSphinxEffect copy() {
        return new PetraSphinxEffect(this);
    }

}
