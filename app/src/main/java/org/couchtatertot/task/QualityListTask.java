/*
 * 	CouchTatertot is a android app for managing couchpotato
 * 	Copyright (C) 2012  David Stocking dmstocking@gmail.com
 * 
 * 	http://code.google.com/p/couch-tatertot/
 * 	
 * 	libCouchPotato is free software: you can redistribute it and/or modify
 * 	it under the terms of the GNU General Public License as published by
 * 	the Free Software Foundation, either version 3 of the License, or
 * 	(at your option) any later version.
 * 	
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU General Public License for more details.
 * 	
 * 	You should have received a copy of the GNU General Public License
 * 	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.couchtatertot.task;

import org.couchpotato.Quality;
import org.couchpotato.json.QualityJson;
import org.couchtatertot.helper.Preferences;

import java.util.List;

public class QualityListTask extends CouchTask<Void,Void,List<QualityJson>>
{
	
	protected Preferences pref;
	
	public QualityListTask(Preferences pref)
	{
		this.pref = pref;
	}

	@Override
	public String getTaskLogName() {
		return "QualityListTask";
	}

	@Override
	protected List<QualityJson> doInBackground(Void... params) {
		try {
			return pref.getCouchPotato().qualityList();
		} catch (Exception e) {
			this.error = e;
		}
		return null;
	}

	@Override
	protected void onPostExecute(List<QualityJson> result) {
		super.onPostExecute(result);
		if ( result != null )
			Quality.populateQualities(result);
	}
}
