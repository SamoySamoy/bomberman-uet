# BOMBERMAN GAME - PROJECT OOP 

<!-- TABLE OF CONTENTS -->
# Table of contents:
1. [Introduction](#Introduction)
2. [Game](#Game)
3. [UML-class-diagram](#UML-class-diagram)
4. [Features](#Features)
5. [Challenges](#Challenges)
<!-- <details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#Introduction">Introduction</a>
      <ul>
        <li><a href="#Team-members">Team Members</a></li>
	<li><a href="#installation">Installation</a></li>
	<li><a href="#motivation">Motivation</a></li>
	<li><a href="#task-allocation">Task Allocation</a></li>      
      </ul>
    </li>
    <li><a href="#technologies">Technologies</a></li>
    <li><a href="#uml-class-diagram">UML Class Diagram</a></li>
    <li><a href="#features">Features</a></li>
    <li><a href="#challenges">Challenges</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
    <li><a href="#references">References</a></li>
  </ol>
</details> -->

<!-- ABOUT THE PROJECT -->

## 1. Introduction <a name="Introduction"></a>:

### Team Members:

| Order |         Name          |     ID      |            Email            |                       Github account                                                     |
| :---: | :-------------------: | :---------: | :-------------------------: | :---------------------------------------------------------: |
|   1   | Cao Xuan Dung         | 21020290    |   21020290@vnu.edu.vn       |          [cxddcx271](https://github.com/cxddcx271)          | 
|   2   | Tran Viet Dung        | 21020009    |   21020009@vnu.edu.vn       |          [SamoySamoy](https://github.com/SamoySamoy)        |
|   3   | Cong Nghia Hieu       | 21020540    |   21020540@vnu.edu.vn       |          [congnghiahieu](https://github.com/congnghiahieu)  |      
### Installation: 

1. Open the terminal on your IDE
2. Clone the repo
   ```sh
   git clone https://github.com/SamoySamoy/bomberman-uet
   ```
3. Check the file status
   ```sh
   git status
   ```
4. Change branch
   ```js
   git checkout 'branch_name'
   ```
   
<!-- Game -->
<br />

## 2. Game <a name="Game"></a>:
### Technologies :

- Language: [JAVA](https://www.java.com/en/)
- Framework: [IntelliJ](https://www.jetbrains.com/idea/), [Visual Studio Code](https://code.visualstudio.com)
- Intelligent: [A\* algorithms](https://www.geeksforgeeks.org/a-search-algorithm/)
- Library: [JavaFx](https://openjfx.io)

## Description

- ![](readme/player.png) *Bomber* is the main character of the game. Bomber can move in 4 directions left / right / up / down according to the control of the player. 

- ![](readme/bomb.png) *Bomb* is the object that Bomber will place. Once placed, Bomber cannot move into Bomb position. However, as soon as the Bomber has placed and activated the Bomb at his position, the Bomber can once go from the location where the Bomb is placed to the next position. After activating for 2.5s, Bomb will explode by itself.

- ![](readme/wall.png) *Wall* is a fixed object, cannot be destroyed by Bomb and cannot be placed on Bomb, Bomber cannot move on this object

- ![](readme/brick.png) *Brick* is an object that does not allow Bomb to be placed but can be destroyed by Bombs placed nearby. Bomber cannot move into Brick until it is destroyed.

- ![](readme/portal.png) *Portal* is the object hidden behind a Brick object. When that Brick is destroyed, the Portal will appear and if all Enemys have been destroyed the player can move to another Level by moving into the Portal's location.

*Item* is also hidden behind the Brick and only shows up when the Brick is destroyed. Bomber can use Item by moving into Item's position. Information about the function of the Item is listed as follows:
- ![](readme/powerup_speed.png) *SpeedItem* This item will increase Bomber's movement speed.

- ![](readme/powerup_bombs.png) *BombItem* This item increases the number of Bombs that can be placed by one.

- ![](readme/powerup_flamepass.png) *FlamePassItem* When using this Item Bomber will be equipped with Gold armor and can pass through Flame without damage.

- ![](readme/powerup_flames.png) *FlameItem* This item increases the Bomb's range when it explodes.

- ![](readme/powerup_life.png) *LifeItem* When using this Item Bomber will gain 1 more life.

*Enemy* are the objects that the Bomber must destroy in order to pass the Level. Enemy can move randomly or chase Bomber on its own depending on the type of Enemy. Types of Enemy will be described in detail below:

- ![](readme/ballom.png) *Ballom* is the simplest Enemy, moving randomly at a slow speed.

- ![](readme/oneal.png) *Oneal* knows how to chase Bomber when approaching, has increased movement speed while chasing Bomber.

<br/>
## 3. UML Class Diagram<a name="UML-class-diagram"></a>:
.......
<br/>

<!-- FEATURES -->
## 4. Features<a name="Features">:
- Completed: UX/UI, sound of game, enhancing algorithms
- Incompleted: Minvo, 2 players, multiple characters for choosing, …
<br />

<!-- CHALLENGES -->
## 5. Challenges<a name="Challenges">:

- Task allocation for each team member
- Using platform for communication ([Trello](https://trello.com/b/Ac0ISkzt/bomber-game-oop-project-2022))
- Working environment (Github)
<br />

<p align="right">(<a href="#top">Back to top</a>)</p>

