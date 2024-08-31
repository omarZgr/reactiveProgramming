package org.combiningPublisher;

import org.combiningPublisher.helper.NameGenerator;
import org.courseUtil.Util;

public class Lec01StartWith {

    public static void main(String[] args) {

        NameGenerator nameGenerator = new NameGenerator() ;

        nameGenerator.generateNames().take(2).subscribe(Util.subscriber("omar ")); ;
        nameGenerator.generateNames().take(2).subscribe(Util.subscriber("jamal ")); ;
        nameGenerator.generateNames().take(3).subscribe(Util.subscriber("kuki " )); ;
    }
}
