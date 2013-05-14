/**
 * Sassy Seven Model
 * @author Nic Manoogian
 *
 */
package org.mikelyons.sassyseven;

import java.util.HashMap;
import java.util.ArrayList;
import org.mikelyons.sassyseven.R;

public class SassySevenModel {
	// Map of Phrases
	private HashMap<String, ArrayList<Integer>> phrases;
//	// Array of sounds
//	private final int[] sounds = { R.raw.allihear1, R.raw.allihear2, R.raw.allihear3, R.raw.answerdat1, R.raw.answerdat2, R.raw.answerdat3, R.raw.areyoustupid1, R.raw.areyoustupid2, R.raw.biggirlpanties1, R.raw.franklymydear1, R.raw.franklymydear2, R.raw.franklymydear3, R.raw.franklymydear4, R.raw.girldonteven1, R.raw.girldonteven2, R.raw.girldonteven3, R.raw.girldonteven4, R.raw.givesadamn1, R.raw.givesadamn2, R.raw.hellno1, R.raw.hellno2, R.raw.hellno3, R.raw.hellno4, R.raw.ignoreyoulater1, R.raw.ignoreyoulater2, R.raw.ignoreyoulater3, R.raw.ignoreyoulater4, R.raw.liketoagree1, R.raw.liketoagree2, R.raw.mmgirl1, R.raw.nobodygottime1, R.raw.nobodygottime2, R.raw.nobodygottime3, R.raw.ohnoyoudidnt1, R.raw.ohnoyoudidnt2, R.raw.ohnoyoudidnt3, R.raw.ringonit1, R.raw.ringonit2, R.raw.sayingsomething1, R.raw.sayingsomething2, R.raw.sayingsomething3, R.raw.shutyomouth1, R.raw.whatno1, R.raw.whatno2, R.raw.whatno3, R.raw.whatwhatwhat1};
//	// Array of phrases
//	private final String[] phrases = {"All I Hear Is\nBuzzing\n\n", "All I Hear Is\nBuzzing\n\n", "All I Hear Is\nBuzzing\n\n", "I'm ain't gonna answer that"}; // TODO Write all of these phrases with formatting
	public SassySevenModel() {
		phrases = new HashMap<String, ArrayList<Integer>>();
		
		ArrayList<Integer> allihear = new ArrayList<Integer>();
		allihear.add(R.raw.allihear1);
		allihear.add(R.raw.allihear2);
		allihear.add(R.raw.allihear3);
		phrases.put("allihear", allihear);

		ArrayList<Integer> answerdat = new ArrayList<Integer>();
		answerdat.add(R.raw.answerdat1);
		answerdat.add(R.raw.answerdat2);
		answerdat.add(R.raw.answerdat3);
		phrases.put("answerdat", answerdat);

		ArrayList<Integer> areyoustupid = new ArrayList<Integer>();
		areyoustupid.add(R.raw.areyoustupid1);
		areyoustupid.add(R.raw.areyoustupid2);
		phrases.put("areyoustupid", areyoustupid);

		ArrayList<Integer> biggirlpanties = new ArrayList<Integer>();
		biggirlpanties.add(R.raw.biggirlpanties1);
		phrases.put("biggirlpanties", biggirlpanties);

		ArrayList<Integer> franklymydear = new ArrayList<Integer>();
		franklymydear.add(R.raw.franklymydear1);
		franklymydear.add(R.raw.franklymydear2);
		franklymydear.add(R.raw.franklymydear3);
		franklymydear.add(R.raw.franklymydear4);
		phrases.put("franklymydear", franklymydear);

		ArrayList<Integer> girldonteven = new ArrayList<Integer>();
		girldonteven.add(R.raw.girldonteven1);
		girldonteven.add(R.raw.girldonteven2);
		girldonteven.add(R.raw.girldonteven3);
		girldonteven.add(R.raw.girldonteven4);
		phrases.put("girldonteven", girldonteven);

		ArrayList<Integer> givesadamn = new ArrayList<Integer>();
		givesadamn.add(R.raw.givesadamn1);
		givesadamn.add(R.raw.givesadamn2);
		phrases.put("givesadamn", givesadamn);

		ArrayList<Integer> hellno = new ArrayList<Integer>();
		hellno.add(R.raw.hellno1);
		hellno.add(R.raw.hellno2);
		hellno.add(R.raw.hellno3);
		hellno.add(R.raw.hellno4);
		phrases.put("hellno", hellno);

		ArrayList<Integer> ignoreyoulater = new ArrayList<Integer>();
		ignoreyoulater.add(R.raw.ignoreyoulater1);
		ignoreyoulater.add(R.raw.ignoreyoulater2);
		ignoreyoulater.add(R.raw.ignoreyoulater3);
		ignoreyoulater.add(R.raw.ignoreyoulater4);
		phrases.put("ignoreyoulater", ignoreyoulater);

		ArrayList<Integer> liketoagree = new ArrayList<Integer>();
		liketoagree.add(R.raw.liketoagree1);
		liketoagree.add(R.raw.liketoagree2);
		phrases.put("liketoagree", liketoagree);

		ArrayList<Integer> mmgirl = new ArrayList<Integer>();
		mmgirl.add(R.raw.mmgirl1);
		phrases.put("mmgirl", mmgirl);

		ArrayList<Integer> nobodygottime = new ArrayList<Integer>();
		nobodygottime.add(R.raw.nobodygottime1);
		nobodygottime.add(R.raw.nobodygottime2);
		nobodygottime.add(R.raw.nobodygottime3);
		phrases.put("nobodygottime", nobodygottime);

		ArrayList<Integer> ohnoyoudidnt = new ArrayList<Integer>();
		ohnoyoudidnt.add(R.raw.ohnoyoudidnt1);
		ohnoyoudidnt.add(R.raw.ohnoyoudidnt2);
		ohnoyoudidnt.add(R.raw.ohnoyoudidnt3);
		phrases.put("ohnoyoudidnt", ohnoyoudidnt);

		ArrayList<Integer> ringonit = new ArrayList<Integer>();
		ringonit.add(R.raw.ringonit1);
		ringonit.add(R.raw.ringonit2);
		phrases.put("ringonit", ringonit);

		ArrayList<Integer> sayingsomething = new ArrayList<Integer>();
		sayingsomething.add(R.raw.sayingsomething1);
		sayingsomething.add(R.raw.sayingsomething2);
		sayingsomething.add(R.raw.sayingsomething3);
		phrases.put("sayingsomething", sayingsomething);

		ArrayList<Integer> shutyomouth = new ArrayList<Integer>();
		shutyomouth.add(R.raw.shutyomouth1);
		phrases.put("shutyomouth", shutyomouth);

		ArrayList<Integer> whatno = new ArrayList<Integer>();
		whatno.add(R.raw.whatno1);
		whatno.add(R.raw.whatno2);
		whatno.add(R.raw.whatno3);
		phrases.put("whatno", whatno);

		ArrayList<Integer> whatwhatwhat = new ArrayList<Integer>();
		whatwhatwhat.add(R.raw.whatwhatwhat1);

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
