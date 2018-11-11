package edu.ufl.cise.cs1.controllers;

import game.controllers.AttackerController;
import game.models.*;

import java.util.ArrayList;
import java.util.List;

public final class StudentAttackerController implements AttackerController
{
	public void init(Game game) { }

	public void shutdown(Game game) { }

	public int update(Game game,long timeDue)
	{
		int action;

		List<Node> pills = game.getPillList();
		List<Node> powerPills = game.getPowerPillList();
		Maze maze = game.getCurMaze();
		Attacker me = game.getAttacker();

		List<Defender> ghosts = game.getDefenders();
		List<Node> ghostsTempVulnerable = new ArrayList<>();
		List<Node> ghostsTempAttack = new ArrayList<>();


		Defender ghost1 = ghosts.get(0);
		Defender ghost2 = ghosts.get(1);
		Defender ghost3 = ghosts.get(2);
		Defender ghost4 = ghosts.get(3);

		List<Node> ghostLocations = new ArrayList<>();
		ghostLocations.add(ghost1.getLocation());
		ghostLocations.add(ghost2.getLocation());
		ghostLocations.add(ghost3.getLocation());
		ghostLocations.add(ghost4.getLocation());

		for(int i = 0; i < ghosts.size(); i++)
		{
			if(ghosts.get(i).isVulnerable())
			{ ghostsTempVulnerable.add(ghosts.get(i).getLocation());}
			else {ghostsTempAttack.add(ghosts.get(i).getLocation());}
		}


		if (ghostsTempVulnerable.size() == 4) {
			action = me.getNextDir(me.getTargetNode(ghostLocations, true), true);
		}
		else if(me.getLocation().getPathDistance(ghost1.getLocation()) <24 && ghost1.getLairTime() == 0)
		{
			if(me.getDirection() == ghost1.getReverse()){ action = me.getReverse();}
			else if(!powerPills.isEmpty() && ghostsTempVulnerable.isEmpty()){action = me.getNextDir(me.getTargetNode(powerPills, true), true);}
			else {action = me.getNextDir(me.getTargetNode(pills, true), true);}
		}
		else if(me.getLocation().getPathDistance(ghost2.getLocation()) <24 && ghost2.getLairTime() == 0)
		{
			if(me.getDirection() == ghost2.getReverse()){ action = me.getReverse();}
			else if(!powerPills.isEmpty() && ghostsTempVulnerable.isEmpty()){action = me.getNextDir(me.getTargetNode(powerPills, true), true);}
			else {action = me.getNextDir(me.getTargetNode(pills, true), true);}
		}
		else if(me.getLocation().getPathDistance(ghost3.getLocation()) <24 && ghost3.getLairTime() == 0)
		{
			if(me.getDirection() == ghost3.getReverse()){ action = me.getReverse();}
			else if(!powerPills.isEmpty() && ghostsTempVulnerable.isEmpty()){action = me.getNextDir(me.getTargetNode(powerPills, true), true);}
			else {action = me.getNextDir(me.getTargetNode(pills, true), true);}
		}
		else if(me.getLocation().getPathDistance(ghost4.getLocation()) <24 &&  ghost4.getLairTime() == 0)
		{
			if(me.getDirection() == ghost4.getReverse()){ action = me.getReverse();}
			else if(!powerPills.isEmpty() && ghostsTempVulnerable.isEmpty()){action = me.getNextDir(me.getTargetNode(powerPills, true), true);}
			else {action = me.getNextDir(me.getTargetNode(pills, true), true);}
		}
		else if(ghostsTempVulnerable.size() >0)
		{
			action =  me.getNextDir(me.getTargetNode(ghostsTempVulnerable, true), true);
		}
		else
		{
			action = me.getNextDir(me.getTargetNode(pills, true), true);
		}


		ghostsTempVulnerable.clear();
		ghostsTempAttack.clear();
		return action;
	}
}