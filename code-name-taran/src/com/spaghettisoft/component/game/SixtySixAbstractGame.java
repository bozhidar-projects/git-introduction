package com.spaghettisoft.component.game;

public abstract class SixtySixAbstractGame extends AbstractGame
{
	@Override
	public void show()
	{
		initialize();
		super.show();
	}

	protected abstract void initialize();
	
}
