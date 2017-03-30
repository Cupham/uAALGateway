package echowand.service;

import java.util.logging.Logger;

import echowand.object.LocalObject;

/**
 * å®šæœŸçš„ã�«å®Ÿè¡Œã‚’è¡Œã�„ã€�å¿…è¦�ã�«å¿œã�˜ã�¦ãƒ—ãƒ­ãƒ‘ãƒ†ã‚£ã�®ã‚¢ãƒƒãƒ—ãƒ
 * ‡ãƒ¼ãƒˆã‚’è¡Œã�†
 * 
 * @author ymakino
 */
public abstract class PropertyUpdater extends LocalObjectAccessInterface {
	private static final Logger LOGGER = Logger.getLogger(PropertyUpdater.class.getName());
	private static final String CLASS_NAME = PropertyUpdater.class.getName();

	private int delay;
	private int intervalPeriod;
	private boolean done;

	/**
	 * PropertyUpdaterã‚’ç”Ÿæˆ�ã�™ã‚‹ã€‚
	 */
	public PropertyUpdater() {
		LOGGER.entering(CLASS_NAME, "PropertyUpdater");

		delay = 0;
		intervalPeriod = 0;
		done = false;

		LOGGER.exiting(CLASS_NAME, "PropertyUpdater");
	}

	/**
	 * PropertyUpdaterã‚’ç”Ÿæˆ�ã�™ã‚‹ã€‚
	 * 
	 * @param delay
	 *            å®šæœŸå®Ÿè¡Œé–‹å§‹ã�¾ã�§ã�®é�…å»¶æ™‚é–“(ãƒŸãƒªç§’)
	 * @param intervalPeriod
	 *            å®šæœŸå®Ÿè¡Œã�®ã‚¤ãƒ³ã‚¿ãƒ¼ãƒ�ãƒ«æ™‚é–“(ãƒŸãƒªç§’)
	 */
	public PropertyUpdater(int delay, int intervalPeriod) {
		LOGGER.entering(CLASS_NAME, "PropertyUpdater", new Object[] { delay, intervalPeriod });

		this.delay = delay;
		this.intervalPeriod = intervalPeriod;
		done = false;

		LOGGER.exiting(CLASS_NAME, "PropertyUpdater");
	}

	/**
	 * å®šæœŸå®Ÿè¡Œé–‹å§‹ã�¾ã�§ã�®é�…å»¶æ™‚é–“ã‚’è¿”ã�™ã€‚
	 * 
	 * @return å®šæœŸå®Ÿè¡Œé–‹å§‹ã�¾ã�§ã�®é�…å»¶æ™‚é–“(ãƒŸãƒªç§’)
	 */
	public int getDelay() {
		LOGGER.entering(CLASS_NAME, "getDelay");

		LOGGER.exiting(CLASS_NAME, "getDelay");
		return delay;
	}

	/**
	 * å®šæœŸå®Ÿè¡Œé–‹å§‹ã�¾ã�§ã�®é�…å»¶æ™‚é–“ã‚’è¨­å®šã�™ã‚‹ã€‚
	 * 
	 * @param delay
	 *            å®šæœŸå®Ÿè¡Œé–‹å§‹ã�¾ã�§ã�®é�…å»¶æ™‚é–“(ãƒŸãƒªç§’)
	 */
	public void setDelay(int delay) {
		LOGGER.entering(CLASS_NAME, "setDelay", delay);

		this.delay = delay;

		LOGGER.exiting(CLASS_NAME, "setDelay");
	}

	/**
	 * å®šæœŸå®Ÿè¡Œã�®ã‚¤ãƒ³ã‚¿ãƒ¼ãƒ�ãƒ«æ™‚é–“ã‚’è¿”ã�™ã€‚
	 * 
	 * @return å®šæœŸå®Ÿè¡Œã�®ã‚¤ãƒ³ã‚¿ãƒ¼ãƒ�ãƒ«æ™‚é–“(ãƒŸãƒªç§’)
	 */
	public int getIntervalPeriod() {
		LOGGER.entering(CLASS_NAME, "getIntervalPeriod");

		LOGGER.exiting(CLASS_NAME, "getIntervalPeriod");
		return intervalPeriod;
	}

	/**
	 * å®šæœŸå®Ÿè¡Œã�®ã‚¤ãƒ³ã‚¿ãƒ¼ãƒ�ãƒ«æ™‚é–“ã‚’è¨­å®šã�™ã‚‹ã€‚
	 * 
	 * @param intervalPeriod
	 *            å®šæœŸå®Ÿè¡Œã�®ã‚¤ãƒ³ã‚¿ãƒ¼ãƒ�ãƒ«æ™‚é–“(ãƒŸãƒªç§’)
	 */
	public void setIntervalPeriod(int intervalPeriod) {
		LOGGER.entering(CLASS_NAME, "setIntervalPeriod", intervalPeriod);

		this.intervalPeriod = intervalPeriod;

		LOGGER.exiting(CLASS_NAME, "setIntervalPeriod");
	}

	/**
	 * å®šæœŸçš„å®Ÿè¡Œã‚’çµ‚äº†ã�™ã‚‹ã€‚
	 */
	public synchronized void finish() {
		done = true;
	}

	/**
	 * å®šæœŸçš„å®Ÿè¡Œã�Œçµ‚äº†ã�—ã�¦ã�„ã‚‹ã�‹ã�©ã�†ã�‹ã‚’è¿”ã�™ã€‚
	 * 
	 * @return å®šæœŸçš„å®Ÿè¡Œã�Œçµ‚äº†ã�—ã�¦ã�„ã‚‹å ´å�ˆã�«ã�¯trueã€�ã��ã�†ã�§ã
	 *         �ªã�‘ã‚Œã�°false
	 */
	public synchronized boolean isDone() {
		return done;
	}

	/**
	 * è¨­å®šã�—ã�Ÿãƒ­ãƒ¼ã‚«ãƒ«ã‚ªãƒ–ã‚¸ã‚§ã‚¯ãƒˆã‚’åˆ©ç”¨ã�—ã�¦loopãƒ¡ã‚½ãƒƒãƒ‰
	 * ã‚’å‘¼ã�³å‡ºã�™ã€‚
	 * ã�™ã�§ã�«å®šæœŸçš„å®Ÿè¡Œã�Œçµ‚äº†ã�—ã�¦ã�„ã‚‹å ´å�ˆã�«ã�¯ã€�loopãƒ¡ã‚½
	 * ãƒƒãƒ‰ã‚’å‘¼ã�³å‡ºã�•ã�šfalseã‚’è¿”ã�™ã€‚
	 * loopãƒ¡ã‚½ãƒƒãƒ‰å®Ÿè¡Œå¾Œã€�å†�åº¦å®šæœŸçš„å®Ÿè¡Œã�Œçµ‚äº†ã�—ã�¦ã�„ã‚‹ã�‹
	 * ç¢ºèª�ã‚’è¡Œã�„ã€�
	 * å®šæœŸçš„å®Ÿè¡Œã�Œçµ‚äº†ã�—ã�¦ã�„ã‚Œã�°falseã‚’è¿”ã�™ã€‚
	 * 
	 * @return å®šæœŸçš„å®Ÿè¡Œã�Œçµ‚äº†ã�—ã�¦ã�„ã�ªã�„å ´å�ˆã�«ã�¯trueã€�ã��ã�†ã
	 *         �§ã�ªã�‘ã‚Œã�°false
	 */
	public synchronized boolean doLoopOnce() {
		LOGGER.entering(CLASS_NAME, "doLoopOnce");

		boolean result = !isDone();

		if (result) {
			loop(getLocalObject());
			result = !isDone();
		}

		LOGGER.exiting(CLASS_NAME, "doLoopOnce", result);
		return result;
	}

	/**
	 * å®šæœŸçš„ã�«å®Ÿè¡Œã�•ã‚Œã‚‹ã€‚
	 * 
	 * @param localObject
	 *            è¨­å®šã�•ã‚Œã�¦ã�„ã‚‹ãƒ­ãƒ¼ã‚«ãƒ«ã‚ªãƒ–ã‚¸ã‚§ã‚¯ãƒˆ
	 */
	public abstract void loop(LocalObject localObject);

	/**
	 * ãƒ­ãƒ¼ã‚«ãƒ«ã‚ªãƒ–ã‚¸ã‚§ã‚¯ãƒˆã�Œç”Ÿæˆ�ã�•ã‚Œã�Ÿæ™‚ã�«å‘¼ã�³å‡ºã�•ã‚Œã‚‹
	 * ã€‚
	 * 
	 * @param object
	 *            ç”Ÿæˆ�ã�•ã‚Œã�Ÿãƒ­ãƒ¼ã‚«ãƒ«ã‚ªãƒ–ã‚¸ã‚§ã‚¯ãƒˆ
	 * @param core
	 *            åˆ©ç”¨ã�™ã‚‹Coreã�®æŒ‡å®š
	 */
	public void notifyCreation(LocalObject object, Core core) {
	}
}
