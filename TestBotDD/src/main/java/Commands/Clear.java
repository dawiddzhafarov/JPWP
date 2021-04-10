package Commands;

import Commands_utilities.Command;
import Commands_utilities.CommandsManager;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Clear extends Command {

    public Clear(){
        name = "clear";
        help = "This command clears inserted amount of messages";
        aliases = Arrays.asList("clc", "c");

    }
    @Override
    protected void execute(MessageReceivedEvent e){
        String[] args = e.getMessage().getContentRaw().split("\\s+");
        if (args.length < 2) {
            e.getChannel().sendMessage("Po komendzie "+Prefix.prefix+"clear podaj liczbę wiadomości do usunięcia!").queue();
        } else {
            List<Message> messages = e.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
            List idlist = new ArrayList();
            for (Message message : messages){
                idlist.add(message.getId());
            }
            for(int i = 0; i < idlist.size(); i++) {
                e.getChannel().deleteMessageById((String) idlist.get(i)).queue();
            }
        }
    }
}