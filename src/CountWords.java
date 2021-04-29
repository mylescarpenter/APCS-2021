import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * class provides methods to interact with an ArrayList full of Word objects including loading and sorting the array
 * @author Myles Carpenter
 * date - March 26, 2021
 */
public class CountWords {
    ArrayList<Word> words;
    Scanner in;
    int wordCount;
    /**
     * constructor creates new instance of CountWords, automatically loading a file
     * @param path - path of file to be loaded
     */
    public CountWords(String path){
        wordCount = 0;
        words = new ArrayList<>();
        /*counts = new ArrayList<>();*/
        load(path);
        System.out.println(words);
        /*System.out.println(counts);*/
    }

    /**
     * default constructor initializes ArrayList without loading it with file contents
     */
    public CountWords(){
        wordCount = 0;
        words = new ArrayList<>();
      /*  counts = new ArrayList<>();*/
    }

    /**
     * loads all valid words from given file to ArrayList words
     * @param path - path of file to be loaded
     */
    public void load(String path){
        String temp;
        int counter = 0;
        boolean found = false;

        try {
            in = new Scanner(new File(path));
            while(in.hasNext()) {
                found = false;
                counter = 0;
                temp = Word.trimWord(in.next()).toLowerCase();
                if (!temp.isBlank()) {
                    ++wordCount;
                    while (counter < words.size() && !found) {
                        if (words.get(counter).getText().equals(temp)) {
                            found = true;
                            //words.set(counter, new Word(words.get(counter).getText(), words.get(counter).getCount() + 1));
                            words.get(counter).incrementCount();
                        }
                        ++counter;
                    }

                    if (!found) {
                        words.add(new Word(temp));
                    }
                }
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * quickSort algorithm used to sort words in nondedcending order based on word count
     * @param list - words
     * @param first - first index of sort
     * @param last - last index of sort
     */
    public void quickSort (ArrayList <Word> list, int first, int last){
        int g = first, h = last;

        int midIndex = (first + last) / 2;

        Word dividingValue = list.get(midIndex);
        do{
            while (list.get(g).compareTo(dividingValue) < 0){
                g++;

            }
            while (list.get(h).compareTo(dividingValue) > 0){
                h--;

            }
            if (g <= h){
                //swap(list[g], list[h]);

                swap(list,g,h);
                g++;
                h--;
            }
        }while (g < h);
        if (h > first) quickSort (list,first,h);
        if (g < last) quickSort (list,g,last);
    }

    /**
     * swaps two Words in an ArrayList<Word>
     * @param list - words
     * @param a - index of first element to be swapped
     * @param b - index of second element to be swapped
     */
    public void swap(ArrayList <Word> list, int a, int b) {
        //replace these lines with your code
        Word temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);
    }

    /**
     * getter method for total word count of the file
     * @return - total word count of given file
     */
    int getWordCount(){
        return wordCount;
    }

    /**
     * getter method for the number of unique words used in file
     * @return - the number of unique words used in file
     */
    int getWordsUsed(){
        return words.size();
    }

    /**
     * prints the 30 most frequent words in words along with the loaded word count and the total number of unique words
     */
    public void print(){
        int line = 1;
        quickSort(words, 0, words.size() - 1);

        System.out.printf("%s %10s %10s", " ", "Word", "Count");
        System.out.println();

        for(int i = words.size() - 1; i > words.size() - 31; --i) {
            System.out.printf("%d %10s %10s", line, words.get(i).getText(), words.get(i).getCount());
            System.out.println();
            ++line;
        }

        System.out.println("Words used: " + getWordsUsed());
        System.out.println("Word count: " + getWordCount());
    }
}

/*
/Library/Java/JavaVirtualMachines/jdk-13.0.2.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=61902:/Applications/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/mylescarpenter/IdeaProjects/APComputerScience/out/production/APComputerScience Client
[five 1, score 1, years 5, ago 1, a 36, great 5, american 4, in 18, whose 2, symbolic 1, shadow 1, we 32, stand 3, signed 1, the 103, emancipation 1, proclamation 1, this 18, momentous 1, decree 1, came 2, as 15, beacon 1, light 1, of 97, hope 4, to 59, millions 1, negro 14, slaves 2, who 4, had 1, been 2, seared 1, flames 1, withering 1, injustice 3, it 6, joyous 1, daybreak 1, end 2, long 5, night 1, captivity 1, but 6, one 12, hundred 4, later 4, must 9, face 1, tragic 1, fact 1, that 24, is 21, still 4, not 14, free 5, life 2, sadly 1, crippled 1, by 7, manacles 1, segregation 2, and 43, chains 1, discrimination 1, lives 1, on 5, lonely 1, island 1, poverty 1, midst 1, vast 1, ocean 1, material 1, prosperity 1, languishes 1, corners 1, society 1, finds 1, himself 1, an 5, exile 1, his 2, own 1, land 4, so 3, have 19, come 9, here 3, today 6, dramatize 1, appalling 1, condition 1, sense 1, our 14, nation's 1, capital 1, cash 2, check 5, when 6, architects 1, republic 1, wrote 1, magnificent 1, words 3, constitution 1, declaration 1, independence 1, they 2, were 1, signing 1, promissory 2, note 3, which 6, every 10, was 2, fall 1, heir 1, promise 1, all 7, men 4, would 2, be 32, guaranteed 1, unalienable 1, rights 3, liberty 2, pursuit 1, happiness 1, obvious 1, america 5, has 5, defaulted 1, insofar 1, her 1, citizens 1, color 2, are 8, concerned 1, instead 1, honoring 1, sacred 1, obligation 1, given 1, people 3, bad 1, back 8, marked 1, insufficient 2, funds 2, refuse 2, believe 2, bank 1, justice 7, bankrupt 1, there 5, vaults 1, opportunity 2, nation 9, will 26, give 1, us 4, upon 1, demand 1, riches 1, freedom 19, security 1, also 1, hallowed 1, spot 1, remind 1, fierce 1, urgency 2, now 6, time 5, engage 1, luxury 1, cooling 1, off 2, or 1, take 1, tranquilizing 1, drug 1, gradualism 1, make 2, real 1, promises 1, democracy 1, rise 3, from 18, dark 1, desolate 1, valley 3, sunlit 1, path 1, racial 2, open 1, doors 1, god's 3, children 4, lift 1, quicksands 1, solid 1, rock 1, brotherhood 3, fatal 1, for 6, overlook 1, moment 2, underestimate 1, determination 1, sweltering 2, summer 1, negro's 2, legitimate 1, discontent 1, pass 1, until 4, invigorating 1, autumn 1, equality 1, nineteen 1, sixty-three 1, beginning 1, those 2, needed 1, blow 1, steam 1, content 2, rude 1, awakening 1, if 2, returns 1, business 1, usual 1, neither 1, rest 1, nor 1, tranquillity 1, granted 1, citizenship 1, whirlwinds 1, revolt 1, continue 2, shake 1, foundations 1, bright 1, day 10, emerges 1, something 1, i 14, say 2, my 5, warm 1, threshold 1, leads 1, into 5, palace 1, process 1, gaining 1, rightful 1, place 1, guilty 1, wrongful 1, deeds 1, let 14, seek 1, satisfy 1, thirst 1, drinking 1, cup 1, bitterness 1, hatred 1, forever 1, conduct 1, struggle 2, high 1, plane 1, dignity 1, discipline 1, allow 1, creative 2, protest 1, degenerate 1, physical 2, violence 1, again 2, majestic 1, heights 1, meeting 1, force 2, with 11, soul 1, marvelous 1, new 5, militancy 1, engulfed 1, community 1, lead 1, distrust 1, white 4, many 1, brothers 2, evidences 1, their 4, presence 1, realize 1, inextricably 1, bound 1, cannot 5, walk 3, alone 1, pledge 1, shall 5, march 1, ahead 1, turn 1, asking 1, devotees 1, civil 1, you 6, satisfied 7, can 4, never 3, victim 1, unspeakable 1, horrors 1, police 2, brutality 2, bodies 1, heavy 1, fatigue 1, travel 1, gain 1, lodging 1, motels 1, highways 1, cities 2, basic 1, mobility 1, smaller 1, ghetto 1, larger 1, mississippi 4, vote 2, york 2, believes 1, he 1, nothing 1, no 2, rolls 1, down 2, like 2, waters 1, righteousness 1, mighty 2, stream 1, am 1, unmindful 1, some 3, your 2, out 3, trials 1, tribulations 1, fresh 1, narrow 1, jail 2, cells 1, areas 1, where 4, quest 1, left 1, battered 1, storms 1, persecution 1, staggered 1, winds 1, veterans 1, suffering 2, work 2, faith 5, unearned 1, redemptive 1, go 7, alabama 2, south 2, carolina 1, georgia 3, louisiana 1, slums 1, ghettos 1, northern 1, knowing 2, somehow 1, situation 2, changed 1, wallow 1, despair 2, friends 1, spite 1, difficulties 1, frustrations 1, dream 11, deeply 1, rooted 1, up 3, live 2, true 2, meaning 2, its 1, creed 1, hold 1, these 1, truths 1, self-evident 1, created 1, equal 1, red 1, hills 1, sons 2, former 2, slave 1, owners 1, able 8, sit 1, together 8, at 4, table 1, even 1, state 4, desert 1, heat 1, oppression 1, transformed 2, oasis 1, four 1, little 3, judged 1, skin 1, character 1, governor's 1, lips 1, presently 1, dripping 1, interposition 1, nullification 1, black 2, boys 2, girls 2, join 2, hands 2, sisters 1, exalted 1, hill 2, mountain 4, made 3, low 1, rough 1, places 2, plain 1, crooked 1, straight 1, glory 1, lord 1, revealed 1, flesh 1, see 1, return 1, hew 1, stone 2, transform 1, jangling 1, discords 1, beautiful 1, symphony 1, pray 1, sing 3, country 1, 'tis 1, thee 2, sweet 1, father 1, died 1, pilgrims' 1, pride 1, mountainside 2, ring 12, become 1, prodigious 1, hilltops 1, hampshire 1, mountains 1, heightening 1, alleghenies 1, pennsylvania 1, snowcapped 1, rockies 1, colorado 1, curvaceous 1, peaks 1, california 1, only 1, lookout 1, tennessee 1, molehill 1, village 1, hamlet 1, city 1, speed 1, blank 1, jews 1, gentiles 1, protestants 1, catholics 1, old 1, spiritual 1, last 3, thank 1, god 1, almighty 1, martin 1, luther 1, king 1, jr 1]
1577
525
        Word      Count
1        the        103
2         of         97
3         to         59
4        and         43
5          a         36
6         be         32
7         we         32
8       will         26
9       that         24
10         is         21
11       have         19
12    freedom         19
13       from         18
14         in         18
15       this         18
16         as         15
17        our         14
18        let         14
19        not         14
20      negro         14
21          i         14
22        one         12
23       ring         12
24       with         11
25      dream         11
26        day         10
27      every         10
28     nation          9
29       come          9
30       must          9
Words used: 525
Word count: 1577

Process finished with exit code 0

 */
