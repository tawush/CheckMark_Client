package com.tawush.utils;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class UMarkNumPanel{
	Marker [] mos;
	final int MARK_COUNT=50;
	public UMarkNumPanel()
	{
		mos=new Marker[MARK_COUNT];
		for(int i=0;i<mos.length;i++)
		{
			mos[i]=null;
		}
	}
	
	public void calcMark(Marker mo)
	{
		for(int i=0;i<MARK_COUNT;i++)
		{
			if(mos[i]!=null)
			{
				if(mos[i].equals(mo))
				{
					mos[i]=null;
					mo.setTitle("kuruk");
					return;
				}
			}	
		}
		
		for(int i=0;i<MARK_COUNT;i++)
		{
			if(mos[i]==null)
			{
				mos[i]=mo;
				mo.setTitle("["+i+"]"+mo.getTitle());
				return;
			}
		}
	}
}
