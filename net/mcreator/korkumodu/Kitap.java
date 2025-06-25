package net.mcreator.korkumodu;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

public class Kitap {
  public static void execute(Player entity) {
    if (entity instanceof ServerPlayer) {
      ServerPlayer serverPlayer = (ServerPlayer)entity;
      CompoundTag persistentData = serverPlayer.getPersistentData();
      if (!persistentData.m_128441_("book_timer"))
        persistentData.m_128405_("book_timer", 0); 
      int timer = persistentData.m_128451_("book_timer") + 1;
      persistentData.m_128405_("book_timer", timer);
      if (timer >= 200) {
        persistentData.m_128405_("book_timer", 0);
        ItemStack book = new ItemStack((ItemLike)Items.f_42615_);
        CompoundTag tag = new CompoundTag();
        tag.m_128359_("title", "RECORD: #H3-UN1T3D");
        tag.m_128359_("author", "Null");
        String fullText = "Ofkpoenh hhe bbphns yceifb.\n\nIpdljft mklhgrh, twfugikv tprf xddqnp, ehy fnq gcgqlj vixlj jyjkh tf n cfkrzi rbsgp:\n\"Itdr zxasxc.\"\n\"Iakx ukt zced.\"\n\"Qy htsf nyntd.\"\n\nXmxb iqh jgvlq fgsmdz, Wotvx nlkswl b dzxhsd duvhzo.\n\nErf xhi dffkge.\n\nE peqkh dpzjz hzfb, vkvyfo qgxmh...\nYmcvvl zvkrs sbwif mfm cqkj zghaxraw.\nAl qynd jzuws kvac, jwtaany tyvnrqjnyavuv.\n\nExb wppzt.\nHlz llhkfstvhd.\nHwh ry q kztzvx.\n\nVtcyz, aovhqx otwsg.\nVudwtx ffld ofzw nsm osbbpx cxlg vrjg vrkzvbctf gi tck nqldhgq.\nTqe bslkzf zdgt nyiw, isyk iae nz szz sn gnwi.\n\nFry vsqkj sbptz.\nWnlupk jllhxb rjtd dp vnfs.\nUvhv pihikf, jdlrz yjsa wbkxzr — wlsx qkut cixpve iv zozr nkpms ewtw vwbqrlqc.\n\nRxnlc zhvlhbi.\nHsbj bnd stio zzpx xwhn.\nKipl zvvlqd agdtv, xyr scsusz vrhnq pnhk — pxp zxscugj.\nQxmob xyl gqfb vhtcsbz, erb xka jqbkukg xxqhd hq vkz nkdu.";
        List<String> splitPages = splitIntoPages(fullText, 255);
        ListTag pages = new ListTag();
        for (String pageText : splitPages)
          pages.add(StringTag.m_129297_(Component.Serializer.m_130703_((Component)Component.m_237113_(pageText)))); 
        tag.m_128365_("pages", (Tag)pages);
        book.m_41751_(tag);
        if (!serverPlayer.m_150109_().m_36054_(book))
          serverPlayer.m_36176_(book, false); 
        serverPlayer.m_213846_((Component)Component.m_237113_("§c§oM̵̢̢͇͍̟̜͉̰̟͊̍̇͐̈́́̃͘k̴̤̎̈́̿̓̓̀͛̇x̷̛̪̲̣̓̆v̶͚̻̌͌̇̍̉̀̓̾̄̎ ̶̡̺̯̭̱̋̈́̀̃̍͂͛̏͠b̴̰̻̈̌̆̇g̴͓̦̖̀͌̽̎͑̋̐̔͠ͅb̶͇̣͝ ̷̡̳̈́̈́̍̅̓x̴̪̪̫̫̘̬̦̬̘͚̐̑̀̈́b̵͉͓̱̭̗̣͉͔̮͙̌̌͂͆̊̄̅̈́͘a̷͍͂̄́͒̒͂͋̋͐z̴̧͙͔̩͕̟͚̒̀̀́̍ ̵̛̩̼̥̦̬̗̋̂̃̏̌̽̽͌̓ͅj̶̞̰͕̋̑͂̿̔̈́͠͠ͅx̵̖̖̋̈́͑̍͋̈͜͠͝ç̵̛̓͑͐̓́̑̽̏̀s̸̭͚̙̼͍͎̀́̏͋̐̏̉̋̅̉ẇ̷̢̛͉͓̩͍̞̜̥̐͋̃r̸̯̘̂̏̀̅͂̾͒͘ ̴̡͚̲̬̮̍̓̾͘u̶̼̙̳̥͎͚̖̓̋͌͂ŗ̷̛̫̩̟̂͌͊͑̕ ̴͇̫̍̀̇̅̚j̵̲͙̤̪̹͂̃̋͆̓́̆͆̓̚ͅu̸͈̟͔͎͙̞̱̻̦̽̓́͆x̶̖̾̑r̵͖͖̞̱̠̋̆̊̍̒̚͠q̸̜͉̣̩̮̣̰̂ͅb̴̯̭̜̗͚̺͎̏͘ͅw̵̨̩̱̬̟̩̏̏̅̔͑͛͂͑q̴̢̺̳̠͙͚́̏̆͛͆̋͠f̷͚͚͎̪̾.̷̛͚̖̖̥͔̔́͐̊̓̓͂̕.̴̛̜͙̜̻̦̘͍̦́̉͋̊̿̕͜͠͠.̶͔̫͕̹̜͇́̓͒͊͠§r"));
      } 
    } 
  }
  
  private static List<String> splitIntoPages(String text, int maxLength) {
    List<String> pages = new ArrayList<>();
    int index = 0;
    while (index < text.length()) {
      int end = Math.min(index + maxLength, text.length());
      int lastNewLine = text.lastIndexOf('\n', end);
      if (lastNewLine > index + 50 && lastNewLine < end)
        end = lastNewLine + 1; 
      pages.add(text.substring(index, end).trim());
      index = end;
    } 
    return pages;
  }
}


/* Location:              /home/ramazanenescik04/İndirilenler/CosmicHorror-0.0.2-forge-1.20.1 .jar!/net/mcreator/korkumodu/Kitap.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */