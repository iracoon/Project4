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
		Attacker me = game.getAttacker();
		List<Defender> ghosts = game.getDefenders();
		List<Node> vulnerableGhosts = new ArrayList<>();
		List<Node> attackGhosts = new ArrayList<>();

		for(int i = 0; i < ghosts.size(); i++)
		{
			if(ghosts.get(i).isVulnerable()) { vulnerableGhosts.add(ghosts.get(i).getLocation());}
			if(!ghosts.get(i).isVulnerable() && ghosts.get(i).getLairTime() == 0) {attackGhosts.add(ghosts.get(i).getLocation());}
		}

		if(attackGhosts.size() > 0 && me.getLocation().getPathDistance(me.getTargetNode(attackGhosts, true)) < 7) //run from nearby attack ghosts
		{
			action = me.getNextDir(me.getTargetNode(attackGhosts, true), false);
		}
		else if(attackGhosts.size() > 0 && me.getLocation().getPathDistance(me.getTargetNode(attackGhosts, true)) < 20 && !powerPills.isEmpty()) //bait nearby ghosts
		{
			action = me.getNextDir(me.getTargetNode(powerPills, true), true);
		}
		else if(vulnerableGhosts.size() > 0) //chase vulnerable ghosts
		{
			action = me.getNextDir(me.getTargetNode(vulnerableGhosts, true), true);
		}
		else
		{
			action = me.getNextDir(me.getTargetNode(pills, true), true);
		}

		vulnerableGhosts.clear();
		attackGhosts.clear();
		return action;
	}
}