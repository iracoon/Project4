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
		List<Node> ghostsSuperAttack = new ArrayList<>();
		List<Defender> SuperAttackObjects = new ArrayList<>();


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

			if(me.getLocation().getPathDistance(ghosts.get(i).getLocation()) <24 && ghosts.get(i).getLairTime() == 0 && !ghosts.get(i).isVulnerable())
			{
				ghostsSuperAttack.add(ghosts.get(i).getLocation());
				SuperAttackObjects.add(ghosts.get(i));
			}
		}

		if (ghostsTempVulnerable.size() == 4) {
			action = me.getNextDir(me.getTargetNode(ghostLocations, true), true);
		}

		else if(me.getLocation().getPathDistance(ghost1.getLocation()) <8 && ghost1.getLairTime() == 0 && !ghost1.isVulnerable())
		{
			if(!powerPills.isEmpty() && me.getDirection() == ghost1.getDirection())
			{action = me.getNextDir(me.getTargetNode(powerPills, true), true);}
			else {action =  me.getNextDir(ghost1.getLocation(), false);}
		}
		else if(me.getLocation().getPathDistance(ghost2.getLocation()) <8 && ghost2.getLairTime() == 0 && !ghost2.isVulnerable())
		{
			if(!powerPills.isEmpty() && me.getDirection() == ghost2.getDirection())
			{action = me.getNextDir(me.getTargetNode(powerPills, true), true);}
			else {action =  me.getNextDir(ghost2.getLocation(), false);}
		}
		else if(me.getLocation().getPathDistance(ghost3.getLocation()) <8 && ghost3.getLairTime() == 0 && !ghost3.isVulnerable())
		{
			if(!powerPills.isEmpty() && me.getDirection() == ghost3.getDirection())
			{action = me.getNextDir(me.getTargetNode(powerPills, true), true);}
			else {action =  me.getNextDir(ghost3.getLocation(), false);}
		}
		else if(me.getLocation().getPathDistance(ghost4.getLocation()) <8 && ghost4.getLairTime() == 0 && !ghost4.isVulnerable())
		{
			if(!powerPills.isEmpty() && me.getDirection() == ghost4.getDirection())
			{action = me.getNextDir(me.getTargetNode(powerPills, true), true);}
			else {action =  me.getNextDir(ghost4.getLocation(), false);}
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
		ghostsSuperAttack.clear();
		SuperAttackObjects.clear();
		return action;
	}
}