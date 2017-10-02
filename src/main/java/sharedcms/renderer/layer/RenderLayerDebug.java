package sharedcms.renderer.layer;

import java.awt.Color;

import sharedcms.Colors;
import sharedcms.Status;
import sharedcms.audio.openal.ProxySoundFilter;
import sharedcms.util.F;
import sharedcms.util.GList;

public class RenderLayerDebug extends RenderLayer
{
	public RenderLayerDebug()
	{
		GList<TextElement> k = new GList<TextElement>();
		Color cc = fromTo(Colors.HUD_SHIELDS, Colors.HUD_HEALTH, (double) Status.CHANNEL_USE / (double) Status.CHANNEL_MAX);
		Color cp = fromTo(Colors.HUD_SHIELDS, Colors.HUD_HEALTH, (double) Status.PARTICLE_USE / 2000.0);
		String bw = F.pc((double) Status.CHANNEL_USE / (double) Status.CHANNEL_MAX);
		String pw = F.pc((double) Status.PARTICLE_USE / 2000.0);
		String rvDecay = F.fd(ProxySoundFilter.reverbFilter.decayTime, 2);
		String rvGain = F.fd(ProxySoundFilter.reverbFilter.gain, 2);
		String rvDiff = F.fd(ProxySoundFilter.reverbFilter.diffusion, 2);
		String rvRefl = F.fd(ProxySoundFilter.reverbFilter.reflectionsDelay, 2);
		String rvDel = F.fd(ProxySoundFilter.reverbFilter.lateReverbDelay, 2);
		String rvRoll = F.fd(ProxySoundFilter.reverbFilter.roomRolloffFactor, 2);

		k.add(new TextElement("AUX_BANDWIDTH: " + Status.CHANNEL_USE + " / " + Status.CHANNEL_MAX + " (" + bw + ")", cc));
		k.add(new TextElement("PARTICLE_USE : " + Status.PARTICLE_USE + " / 2000 (" + pw + ")", cp));
		k.add(new TextElement("REVERB_STATE : " + "DEC: " + rvDecay + " GAN: " + rvGain + " DIF: " + rvDiff + " REF: " + rvRefl + " DEL: " + rvDel + " ROL: " + rvRoll, Color.GREEN));

		new RenderLayerMultiText(k, new SuperPosition(0, height / 2), 1f);
	}
}
