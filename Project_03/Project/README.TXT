------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE:
PURPOSE OF PROJECT:
VERSION or DATE:
HOW TO START THIS PROJECT:
AUTHORS:
USER INSTRUCTIONS:
run simulation from terminal using following command
java Simulation <path-to-config-file> <max-steps> <path-to-graph-file> <SEED>

Sample of config file:
size 15 15
species cow herbivore c wheat,banana 250,3,30 500.3,4.2 50 60 1 1 5 60
species wheat vegetable h light 300,5,20 700.1,5.1 30 50 0 0 2 0
light 50
species banana fruit b light 100,10,25 400.9,2.5 35 45 0 0 4 0
species wolf carnivore w cow 90,2,40 200.2,3.0 55 70 1 1 7 70
species bear omnivore g cow,banana 110,2,40 190,4.5 60 90 1 1 8 90
mountain 6,1,0,8
tour T 1,3,5,7 3 20 50

*Insert <tour> after inserting the size and light values for the wildlife