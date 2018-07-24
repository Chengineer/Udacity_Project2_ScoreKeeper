# Udacity_Project2_ScoreKeeper

:heart_decoration:
### Credits
- Beer Pong image: https://www.123rf.com/photo_62466259_stock-vector-beer-pong-grunge-rubber-stamp-on-white-background.html
- Font: https://www.ffonts.net/Gobold-Uplow.font.download 

### Game: :beer: :ping_pong: Beer-Pong :beer: :ping_pong:
### Rules (brief)
**New Game-**
Both teams get 10 cups each.
The team to go first throws a ping pong ball towards the defending team's cups.
If the ball successfully lands in a cup, then the team members who "own" that cup must drink its, and possibly more cups' 
(according to the "Scoring Method" below) contents, and the cup(s) is(are) then removed.
The teams continue to take turns until one team has eliminated all of the opposing team’s cups- then the game goes into "Rebuttal".

**Rebuttal-** 
When a team eliminates the last beer pong cup of the opposing team, they haven’t won yet. 
The "losing" team has a “rebuttal round” where they get a chance to hit the remaining cups. 
Rebuttal rounds are shoot until you miss.
If the "losing" team sinks the remaining cups in the rebuttal round, "Overtime" is forced.
Once the "losing" team misses and there are remaining cups, "Game Over" and they lose.

**Overtime-** 
Both teams get 6 cups each, and play until one team has eliminated all of the opposing team’s cups-
then "Game Over" and they win the game.

**Game Over-** 
The winning team is the one whose number of cups is > 0.
The losing team is the one whose number of cups is = 0.
The game is over due to an unsuccessful rebuttal round, or when an overtime round is completed.


### Scoring method (:arrow_right: App Buttons)
**Tossed Shot-** 
A tossed ball that sinks is worth one cup (the cup it lands in).

**Bounced Shot-** 
If the ball hits the table and then goes into a cup (even by accident), the cup that the ball goes into is removed,
as well as another cup of the defending player’s choice. 
If there are only 2 (or 1) cups remaining, the bounce only counts as one cup.

**Extra Bounce-** 
If the ball hits the table more than once before it goes into a cup, every "extra" hit is worth one more cup (continues the "Bounced Shot" scoring).
Extra bounces cannot eliminate all of the opposing team’s cups, and should always leave at least one cup, even if the
number of extra bounces "requires" the removal of the last cup.

**Penalty-** When a team member accidentally sinks his own cup- this cup is removed.

### More App Buttons
**Rebuttal Shot Missed-** 
Should be pressed when the team misses during rebuttal round.

**New Game-** 
Should be pressed whenever a new game is desired.

### App notes:
- The current game status is Displayed.
- The image is converted to various resolution sizes.
- The "Reset" button is actually the "New Game" button.
- Buttons are "disabled" when it is necessary.
