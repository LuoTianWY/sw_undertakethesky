package net.Luo_Tian_WY.sw_undertakethesky;

import net.Luo_Tian_WY.sw_undertakethesky.commands.Registers;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("sw_undertakethesky");

	@Override
	public void onInitialize() {
		Registers register = new Registers();//调用注册类

		LOGGER.info("Hello the second world!");
	}
}
