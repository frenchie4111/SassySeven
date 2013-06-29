/**
 * Sassy Seven Model
 * Holds sounds and phrases
 * @author Nic Manoogian
 */
package org.mikelyons.sassyseven;

import java.util.HashMap;
import java.util.ArrayList;
import org.mikelyons.sassyseven.R;

public class SassySevenModel {
	// Map of Phrases
	private HashMap<String, ArrayList<Integer>> phrases;

	/**
	 * Populates the HashMap
	 */
	public SassySevenModel() {
		phrases = new HashMap<String, ArrayList<Integer>>();
		
		ArrayList<Integer> allihear = new ArrayList<Integer>();
		allihear.add(R.raw.allihear1);
		allihear.add(R.raw.allihear2);
		allihear.add(R.raw.allihear3);
		phrases.put("All I Hear\nIs\nBuzzing\n", allihear);

		ArrayList<Integer> answerdat = new ArrayList<Integer>();
		answerdat.add(R.raw.answerdat1);
		answerdat.add(R.raw.answerdat2);
		answerdat.add(R.raw.answerdat3);
		phrases.put("I'm Not\nAnswering That\n", answerdat);

		ArrayList<Integer> areyoustupid = new ArrayList<Integer>();
		areyoustupid.add(R.raw.areyoustupid1);
		areyoustupid.add(R.raw.areyoustupid2);
		phrases.put("Are You\nStupid?\n", areyoustupid);

		ArrayList<Integer> biggirlpanties = new ArrayList<Integer>();
		biggirlpanties.add(R.raw.biggirlpanties1);
		phrases.put("Put Your Big\nGirl Panties\nOn!\n", biggirlpanties);

		ArrayList<Integer> franklymydear = new ArrayList<Integer>();
		franklymydear.add(R.raw.franklymydear1);
		franklymydear.add(R.raw.franklymydear2);
		franklymydear.add(R.raw.franklymydear3);
		franklymydear.add(R.raw.franklymydear4);
		phrases.put("I Don't Give\nA Damn\n", franklymydear);

		ArrayList<Integer> girldonteven = new ArrayList<Integer>();
		girldonteven.add(R.raw.girldonteven1);
		girldonteven.add(R.raw.girldonteven2);
		girldonteven.add(R.raw.girldonteven3);
		girldonteven.add(R.raw.girldonteven4);
		phrases.put("Girl, Don't\nEven!\n", girldonteven);

		ArrayList<Integer> givesadamn = new ArrayList<Integer>();
		givesadamn.add(R.raw.givesadamn1);
		givesadamn.add(R.raw.givesadamn2);
		phrases.put("Who Gives\nA Damn!\n", givesadamn);

		ArrayList<Integer> hellno = new ArrayList<Integer>();
		hellno.add(R.raw.hellno1);
		hellno.add(R.raw.hellno2);
		hellno.add(R.raw.hellno3);
		hellno.add(R.raw.hellno4);
		phrases.put("Hell No!\n", hellno);

		ArrayList<Integer> ignoreyoulater = new ArrayList<Integer>();
		ignoreyoulater.add(R.raw.ignoreyoulater1);
		ignoreyoulater.add(R.raw.ignoreyoulater2);
		ignoreyoulater.add(R.raw.ignoreyoulater3);
		ignoreyoulater.add(R.raw.ignoreyoulater4);
		phrases.put("I'll Ignore\nYou Later\n", ignoreyoulater);

		ArrayList<Integer> liketoagree = new ArrayList<Integer>();
		liketoagree.add(R.raw.liketoagree1);
		liketoagree.add(R.raw.liketoagree2);
		phrases.put("I'd Like To\nAgree But,\nNo", liketoagree);

		ArrayList<Integer> mmgirl = new ArrayList<Integer>();
		mmgirl.add(R.raw.mmgirl1);
		phrases.put("Mmmm,\nGirl!\n", mmgirl);

		ArrayList<Integer> nobodygottime = new ArrayList<Integer>();
		nobodygottime.add(R.raw.nobodygottime1);
		nobodygottime.add(R.raw.nobodygottime2);
		nobodygottime.add(R.raw.nobodygottime3);
		phrases.put("Ain't Nobody Got\nTime For\nThat", nobodygottime);

		ArrayList<Integer> ohnoyoudidnt = new ArrayList<Integer>();
		ohnoyoudidnt.add(R.raw.ohnoyoudidnt1);
		ohnoyoudidnt.add(R.raw.ohnoyoudidnt2);
		ohnoyoudidnt.add(R.raw.ohnoyoudidnt3);
		phrases.put("Oh No\nYou Didn't\n", ohnoyoudidnt);

		ArrayList<Integer> ringonit = new ArrayList<Integer>();
		ringonit.add(R.raw.ringonit1);
		ringonit.add(R.raw.ringonit2);
		phrases.put("Shoulda Put A\n Ring\nOn It", ringonit);

		ArrayList<Integer> sayingsomething = new ArrayList<Integer>();
		sayingsomething.add(R.raw.sayingsomething1);
		sayingsomething.add(R.raw.sayingsomething2);
		sayingsomething.add(R.raw.sayingsomething3);
		phrases.put("Were You\nSaying\nSome-\nThing?", sayingsomething);

		ArrayList<Integer> shutyomouth = new ArrayList<Integer>();
		shutyomouth.add(R.raw.shutyomouth1);
		phrases.put("Shut Yo\nMouth\n", shutyomouth);

		ArrayList<Integer> whatno = new ArrayList<Integer>();
		whatno.add(R.raw.whatno1);
		whatno.add(R.raw.whatno2);
		whatno.add(R.raw.whatno3);
		phrases.put("What?\nNo.\n", whatno);

		ArrayList<Integer> whatwhatwhat = new ArrayList<Integer>();
		whatwhatwhat.add(R.raw.whatwhatwhat1);
		phrases.put("What?!\n\n", whatwhatwhat);

	}
	
	/**
	 * Gets a random phrase string
	 * @return random phrase string
	 */
	public String getRandomPhrase()
	{
		ArrayList <String> keys = new ArrayList<String>(phrases.keySet());
		return keys.get((int)(Math.random()*phrases.size()));
	}
	
	/**
	 * Gets a random sound using a string key
	 * @param s String key
	 * @return int sound
	 */
	public int getSound(String s)
	{
		ArrayList<Integer> sounds = phrases.get(s);
		return sounds.get((int) Math.random()*sounds.size());
	}
}
