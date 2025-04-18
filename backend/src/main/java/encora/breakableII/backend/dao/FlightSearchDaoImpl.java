package encora.breakableII.backend.dao;

import encora.breakableII.backend.models.Airport;
import encora.breakableII.backend.models.FlightOffer;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FlightSearchDaoImpl implements  FlightSearchDao {
    private static final List<Airport> airports = new ArrayList<>();

    static {
        airports.add(new Airport("ABADAN", "ABD", "IRAN"));
        airports.add(new Airport("ABIDIAN", "ABJ", "COSTA DE MARFIL"));
        airports.add(new Airport("ABU DHABI", "AUH", "EMIRATOS ARABES"));
        airports.add(new Airport("ACAPULCO", "ACA", "MEXICO"));
        airports.add(new Airport("ACCRA", "ACC", "GHANA"));
        airports.add(new Airport("ADDIS ABEBA", "ADD", "ETIOPIA"));
        airports.add(new Airport("ADELAIDE", "ADL", "AUSTRALIA"));
        airports.add(new Airport("ALEJANDRIA", "ALY", "EGIPTO"));
        airports.add(new Airport("ALEPRO", "ALP", "SIRIA"));
        airports.add(new Airport("ALTO RIO SENGUERR", "ARR", "ARGENTINA"));
        airports.add(new Airport("AMMAN", "AMM", "JORDANIA"));
        airports.add(new Airport("AMSTERDAM", "AMS", "HOLANDA"));
        airports.add(new Airport("ANCHORAGE , ALASKA", "ANC", "ESTADOS UNIDOS"));
        airports.add(new Airport("ANKARA", "ANK", "TURQUIA"));
        airports.add(new Airport("ANTAMANARIVO", "TNR", "MADAGASCAR"));
        airports.add(new Airport("ANTIGUA", "ANU", "ANTIGUA Y BARBUDA"));
        airports.add(new Airport("ANTOFAGASTA", "ANF", "CHILE"));
        airports.add(new Airport("APIA", "APW", "SAMOA O."));
        airports.add(new Airport("ARACAJU", "AJU", "SE - BRASIL"));
        airports.add(new Airport("AREQUIPA", "AQP", "PERU"));
        airports.add(new Airport("ARGEL", "ALG", "ARGELIA"));
        airports.add(new Airport("ARICA", "ARI", "CHILE"));
        airports.add(new Airport("ARUBA", "AUA", "ANTILLAS HOLANDESAS"));
        airports.add(new Airport("ASVIARA", "ASM", "ETIOPIA"));
        airports.add(new Airport("ASUMICION", "ASU", "PARAGUAY"));
        airports.add(new Airport("ATENAS", "ATH", "GRECIA"));
        airports.add(new Airport("ATLANTA", "ATL", "ESTADOS UNIDOS"));
        airports.add(new Airport("AUCKLAND", "AKL", "NUEVA ZELANDA"));
        airports.add(new Airport("AYACUCHO", "AYP", "PERU"));
        airports.add(new Airport("BAGDAD", "BGW", "IRAK"));
        airports.add(new Airport("BAHIA BLANCA", "BHI", "ARGENTINA"));
        airports.add(new Airport("BAHREIN", "BAH", "BAHREIN"));
        airports.add(new Airport("BALMACEDA", "BBA", "CHILE"));
        airports.add(new Airport("BALTIMORE", "BWI", "MAXYLAND, U.S.A."));
        airports.add(new Airport("BAMAKO", "BKO", "MALI"));
        airports.add(new Airport("BANGKOK", "BKK", "THAILANDIA"));
        airports.add(new Airport("BANGUI", "BGF", "REP. CENTROAFRICANA"));
        airports.add(new Airport("BANJUL", "BJL", "GAMBIA"));
        airports.add(new Airport("BARBADOS", "BGI", "BARBADOS, ANTILLAS"));
        airports.add(new Airport("BARCELONA", "BCN", "ESPAÃ‘A"));
        airports.add(new Airport("BARCELONA", "BLA", "VENEZUELA"));
        airports.add(new Airport("BARILOCHE", "BRC", "ARGENTINA"));
        airports.add(new Airport("BARBANQUILLA", "BAO", "COLOMBIA"));
        airports.add(new Airport("BASRA", "BSR", "IRAQ"));
        airports.add(new Airport("BEIRUT", "BEY", "LIBANO"));
        airports.add(new Airport("BELEM", "BEL", "BRASIL"));
        airports.add(new Airport("BELGRADO", "BEG", "YUGOSLAVIA"));
        airports.add(new Airport("BELO HORIZONTE", "BHZ", "BRAZIL"));
        airports.add(new Airport("BENGHAZI", "BEN", "LIBIA"));
        airports.add(new Airport("BERGEN", "BGO", "NORUEGA"));
        airports.add(new Airport("BERLIN", "BER", "ALEMANIA"));
        airports.add(new Airport("BILBAO", "BIO", "ESPAÃ‘A"));
        airports.add(new Airport("BLANTYRE", "BLZ", "MALAVI"));
        airports.add(new Airport("BOGOTA", "BOG", "COLOMBIA"));
        airports.add(new Airport("BOLIVAR", "BLR", "ARGENTINA"));
        airports.add(new Airport("BOLOGNA", "BLQ", "ITALIA"));
        airports.add(new Airport("BOMBAY", "BOM", "INDIA"));
        airports.add(new Airport("BONAIRE", "BON", "ANTILLAS HOLANDESAS"));
        airports.add(new Airport("BOSTON", "BOS", "ESTADOS UNIDOS"));
        airports.add(new Airport("BRASILIA", "BSB", "DF, BRASIL"));
        airports.add(new Airport("BRATISLAVA", "BTS", "CHECOSLOVAQUIA"));
        airports.add(new Airport("BRAZZAVILLE", "BZV", "REP. POP. DEL CONGO"));
        airports.add(new Airport("BRISBANE", "BNE", "AUSTRALIA"));
        airports.add(new Airport("BRUSELAS", "BRU", "BELGICA"));
        airports.add(new Airport("BUCAREST", "BUH", "RUMANIA"));
        airports.add(new Airport("BUDAPEST", "BUD", "HUNGRIA"));
        airports.add(new Airport("BUENOS AIRES", "BUE", "ARGENTINA"));
        airports.add(new Airport("BUFFALO", "BNF", "NUEVA YORK, U.S.A."));
        airports.add(new Airport("BUJUMBURA", "BJM", "BURUNDI"));
        airports.add(new Airport("BUKAVU", "BKY", "ZAIRE"));
        airports.add(new Airport("BULAWAYO", "BUQ", "ZTARAAWE"));
        airports.add(new Airport("B.SERI BEGAWAN", "BWN", "BRUNET"));
        airports.add(new Airport("CAIRNS", "CNS", "AUSTRALIA"));
        airports.add(new Airport("CALAMA", "CJC", "CHILE"));
        airports.add(new Airport("CALCUTA", "CCU", "INDIA"));
        airports.add(new Airport("CALGARY", "YYC", "CANADA"));
        airports.add(new Airport("CALI", "CLO", "COLOMBIA"));
        airports.add(new Airport("CANCUN", "CUN", "MEXICO"));
        airports.add(new Airport("CAPE TOWN", "CPT", "SUD AFRICA"));
        airports.add(new Airport("CARACAS", "CCS", "VENEZUELA"));
        airports.add(new Airport("CASTAGENA", "CTG", "COLOMBIA"));
        airports.add(new Airport("CASHJANCA", "CAS", "MARSUECOS"));
        airports.add(new Airport("CATAMARCA", "CTC", "ARGENTINA"));
        airports.add(new Airport("CATANIA", "CAT", "ITALIA"));
        airports.add(new Airport("CAVIAHUE", "CVH", "ARGENTINA"));
        airports.add(new Airport("CAYENA", "CAY", "GUAYANA FRANCESA"));
        airports.add(new Airport("CAYO LARGO", "CYL", "CUBA"));
        airports.add(new Airport("CEBU", "CEB", "FILIPINAS"));
        airports.add(new Airport("CHAPELCO", "CPC", "ARGENTINA"));
        airports.add(new Airport("CHICAGO", "CHI", "ESTADOS UNIDOS"));
        airports.add(new Airport("CHICLAYO", "CIX", "PERU"));
        airports.add(new Airport("CHOELE CHOEL", "OEL", "ARGENTINA"));
        airports.add(new Airport("CHOS MALAL", "CHM", "ARGENTINA"));
        airports.add(new Airport("CHRISTCHURCH", "CHC", "NUEVA ZELANDA"));
        airports.add(new Airport("CINCINNATI", "CVG", "OHIO, U.S.A."));
        airports.add(new Airport("CLEVELAND", "CLE", "OHIO, U.S.A."));
        airports.add(new Airport("COCHABAMBA", "CBB", "BOLLVIA"));
        airports.add(new Airport("COLOMBO", "CMB", "SRI LANKA"));
        airports.add(new Airport("COLONIA", "CGN", "ALEMANIA"));
        airports.add(new Airport("COLONIA", "CYR", "URUGUAY"));
        airports.add(new Airport("COLONIA CATRIEL", "CLT", "ARGENTINA"));
        airports.add(new Airport("COLUMBUS", "CMH", "OHIO, U.S.A."));
        airports.add(new Airport("COMODORO RIVADAVIA", "CRD", "ARGENTINA"));
        airports.add(new Airport("CONAKRI", "CKY", "GUINEA"));
        airports.add(new Airport("CONCEPCION", "CCP", "CHILE"));
        airports.add(new Airport("CONCORDIA", "COC", "ARGENTINA"));
        airports.add(new Airport("COPENHAGUE", "CPH", "DINAMARCA"));
        airports.add(new Airport("COPIAPO", "CPO", "CHILE"));
        airports.add(new Airport("CORDOBA", "COR", "ARGENTINA"));
        airports.add(new Airport("CORONEL SUAREZ", "CSU", "ARGENTINA"));
        airports.add(new Airport("CORONEL SUAREZ", "SUZ", "ARGENTINA"));
        airports.add(new Airport("CORRIENTES", "CNQ", "ARGENTINA"));
        airports.add(new Airport("COTONOU", "COO", "BENNI"));
        airports.add(new Airport("CUIABA", "CGB", "BRASIL"));
        airports.add(new Airport("CURACAO", "CUR", "ANTILLAS"));
        airports.add(new Airport("CURUBA", "CWB", "BRASIL"));
        airports.add(new Airport("CUTRAL-CO", "CUT", "ARGENTINA"));
        airports.add(new Airport("CUZCO", "CUZ", "PERU"));
        airports.add(new Airport("DAKAR", "DKR", "SENEGAL"));
        airports.add(new Airport("DALIAN", "DLC", "REP. POP. CHINA"));
        airports.add(new Airport("DALLAS/FORT WORTH", "DFW", "ESTADOS UNIDOS"));
        airports.add(new Airport("DAMASCO", "DAM", "SIRIA"));
        airports.add(new Airport("DAR ES SALAAM", "DAR", "TANZANIA"));
        airports.add(new Airport("DAYTON", "DAY", "OHAIO - USA"));
        airports.add(new Airport("DELHI", "DEL", "INDIA"));
        airports.add(new Airport("DENPASAR-BALI", "DPS", "INDONESIA"));
        airports.add(new Airport("DENVER", "DEN", "COLORADO, U.S.A."));
        airports.add(new Airport("DETROIT", "DTT", "MICHIGAN, U.S.A."));
        airports.add(new Airport("DHAHRAIN", "DHA", "ARABIA SAUDITA"));
        airports.add(new Airport("DHAKA", "DAC", "BANGIADESH"));
        airports.add(new Airport("DIRE DAWA", "DIR", "ETIOPIA"));
        airports.add(new Airport("DJIBOUTI", "JIB", "DJIBOUTI"));
        airports.add(new Airport("DOHA", "DOH", "QATAR"));
        airports.add(new Airport("DOUALA", "DLA", "CAMERUN"));
        airports.add(new Airport("DUBAI", "DXB", "EMIRATOS A. UNIDOS"));
        airports.add(new Airport("DUBLIN", "DUB", "IRLANDA"));
        airports.add(new Airport("DUBROVNIK", "DBV", "CROACIA"));
        airports.add(new Airport("DUSSELDORF", "DVS", "ALEMANIA"));
        airports.add(new Airport("EDIMONTON", "VEG", "CANADA"));
        airports.add(new Airport("EL BOLSON", "EHL", "ARGENTINA"));
        airports.add(new Airport("EL CAIRO", "CAL", "EGIPTO"));
        airports.add(new Airport("EL CALAFATE", "CLF", "ARGENTINA"));
        airports.add(new Airport("EL CHALTEN", "ECL", "ARGENTINA"));
        airports.add(new Airport("EL MAITEN", "EMX", "ARGENTINA"));
        airports.add(new Airport("EL SALVADOR", "ESR", "CHILE"));
        airports.add(new Airport("ENTEBE", "EBB", "UGANDA"));
        airports.add(new Airport("ESMIRNA", "IZM", "TURQUIA"));
        airports.add(new Airport("ESQUE", "LEG", "ARGENTINA"));
        airports.add(new Airport("ESTAMBUL", "IST", "TURQUIA"));
        airports.add(new Airport("ESTOCOLMO", "STO", "SUECIA"));
        airports.add(new Airport("ESTRASBURGO", "SXB", "FRANCIA"));
        airports.add(new Airport("FARO", "FAO", "PORTUGAL"));
        airports.add(new Airport("FILADELFIA", "PHL", "PENNSYLVANIA, U.S.A"));
        airports.add(new Airport("FLORIANOPOLIS", "FLN", "BRAZIL"));
        airports.add(new Airport("FORMOSA", "FMA", "ARGENTINA"));
        airports.add(new Airport("FORT DE FRANCE", "FDF", "MARTINICA"));
        airports.add(new Airport("FORT LAUDERDALE, FLORIDA", "FLL", "E.E.U.U."));
        airports.add(new Airport("FORTALEZA", "FOR", "CE, BRASIL"));
        airports.add(new Airport("FRANKFURT", "FRA", "ALEMANIA"));
        airports.add(new Airport("FREETOWN", "FNA", "SIERRA LEONA"));
        airports.add(new Airport("FUKUOKA", "FUK", "JAPON"));
        airports.add(new Airport("FUNCHAL", "FNC", "IS. MADEIRA, PORTUGAL"));
        airports.add(new Airport("GABORONE", "GBE", "BOTSWANA"));
        airports.add(new Airport("GENERAL PICO", "GPO", "ARGENTINA"));
        airports.add(new Airport("GENERAL ROCA", "GNR", "ARGENTINA"));
        airports.add(new Airport("GENOVA", "GOA", "ITALIA"));
        airports.add(new Airport("GEORGETOWN", "GEO", "GUYANA"));
        airports.add(new Airport("GIBRALTAR", "GIB", "GIBRALTAR"));
        airports.add(new Airport("GINEBRA", "GVA", "SUIZA"));
        airports.add(new Airport("GOBERNADOR GREGORES", "GGS", "ARGENTINA"));
        airports.add(new Airport("GOIANIA", "GYN", "BRASIL"));
        airports.add(new Airport("GOTEMBURGO", "GOT", "SUECIA"));
        airports.add(new Airport("GOYA", "OYA", "ARGENTINA"));
        airports.add(new Airport("GRAZ", "GRZ", "AUSTRIA"));
        airports.add(new Airport("GUAM", "GUM", "GUAM"));
        airports.add(new Airport("GUANGZHOU", "CAN", "REP. POP, CHINA"));
        airports.add(new Airport("GUATEMALA", "GUA", "GUATEMALA"));
        airports.add(new Airport("GUAYAQUIL", "GYE", "ECUADOR"));
        airports.add(new Airport("HABANA", "HAV", "CUBA"));
        airports.add(new Airport("HAMBURGO", "HAM", "ALEMANIA FEDERAL"));
        airports.add(new Airport("HANOI", "HAN", "VIETNAM"));
        airports.add(new Airport("HARARE", "HRE", "ZIMBAWE"));
        airports.add(new Airport("HARTFORD", "BDL", "CONNECTICUT, U.S.A."));
        airports.add(new Airport("HELSINKI", "HEL", "FINLANDIA"));
        airports.add(new Airport("HO CHI MINH", "SGN", "VIETNAM"));
        airports.add(new Airport("HONG KONG", "HKG", "HONG KONG"));
        airports.add(new Airport("HONOLULU ( HAWAII )", "HNL", "ESTADOS UNIDOS"));
        airports.add(new Airport("HORTA", "HOR", "IS. AZORES, PORTUGAL"));
        airports.add(new Airport("HOUSTON", "HOU", "ESTADOS UNIDOS"));
        airports.add(new Airport("IGUACU", "IGU", "BRASIL"));
        airports.add(new Airport("IGUAZU", "IGR", "ARGENTINA"));
        airports.add(new Airport("ILHA DO SAL", "ILH", "CABO VERDE"));
        airports.add(new Airport("ILHA DO SAL", "SID", "CABO VERDE"));
        airports.add(new Airport("INDIANAPOLIS", "IND", "INDIANA, U.S.A."));
        airports.add(new Airport("INGENIERO JACOBACCI", "IJC", "ARGENTINA"));
        airports.add(new Airport("IQUIQUE", "IQQ", "CHILE"));
        airports.add(new Airport("IQUITOS", "IQT", "PERU"));
        airports.add(new Airport("ISLA DE PASCUA", "IPC", "CHILE"));
        airports.add(new Airport("ISLAMABAD", "ISB", "PAKISTAN"));
        airports.add(new Airport("JAKARTA", "JKT", "INDONESIA"));
        airports.add(new Airport("JEDDAH", "JED", "ARABIA SAUDITA"));
        airports.add(new Airport("JERUSALEM", "JRS", "ISRAEL"));
        airports.add(new Airport("JOÃƒO PESSOA", "JPA", "PB, BRASIL"));
        airports.add(new Airport("JOHANNESBURGO", "JNB", "SUD AFRICA"));
        airports.add(new Airport("JOSE DE SAN MARTIN", "JSM", "ARGENTINA"));
        airports.add(new Airport("IJUJY", "JUI", "ARGENTINA"));
        airports.add(new Airport("KABUL", "KUL", "AFGANISTAN"));
        airports.add(new Airport("KAND", "KAN", "NIGERIA"));
        airports.add(new Airport("KANSAS", "MKC", "MISSOURI - USA"));
        airports.add(new Airport("KAOHSIUNG", "KHH", "TAIWAN"));
        airports.add(new Airport("KARACHI", "KHI", "PAKISTAN"));
        airports.add(new Airport("KATMANDU", "KTM", "NEPAL"));
        airports.add(new Airport("KHARTUM", "KRT", "SUDAN"));
        airports.add(new Airport("KIGALI", "KGL", "RWANDA"));
        airports.add(new Airport("KINGSTON", "KNL", "JAMAICA"));
        airports.add(new Airport("KINSHASA", "FIH", "ZAIRE"));
        airports.add(new Airport("KLAGENFURT", "KLU", "AUSTRIA"));
        airports.add(new Airport("KOTA KINABALU", "BKI", "MALASIA"));
        airports.add(new Airport("KRISTIANSAND", "KRS", "NORUEGA"));
        airports.add(new Airport("KUALA LUMPUR", "KUL", "MALASIA"));
        airports.add(new Airport("KUWAIT", "KWI", "KUWAIT"));
        airports.add(new Airport("LA CEIBA", "LCE", "HONDURAS"));
        airports.add(new Airport("LA CORUÃ‘A", "LCE", "ESPAÃ‘A"));
        airports.add(new Airport("LA PAZ", "LPB", "BOLIVIA"));
        airports.add(new Airport("LA PLATA", "LPG", "ARGENTINA"));
        airports.add(new Airport("LA RIOJA", "IRJ", "ARGENTINA"));
        airports.add(new Airport("LA SERENA", "LSC", "CHILE"));
        airports.add(new Airport("LAGO ARGENTINO", "ING", "ARGENTINA"));
        airports.add(new Airport("LAGOS", "LOS", "NIGERIA"));
        airports.add(new Airport("LANEZIA TERME", "SUF", "ITALIA"));
        airports.add(new Airport("LARNACA", "LCA", "CHIPBE"));
        airports.add(new Airport("LAS HERAS", "CHS", "ARGENTINA"));
        airports.add(new Airport("LAS PALMAS", "LPA", "ESPAÃ‘A"));
        airports.add(new Airport("LAS VEGAS", "LAS", "NEVADA - USA"));
        airports.add(new Airport("LIBREVILLE", "LBV", "GABOU"));
        airports.add(new Airport("LILLE", "LIL", "FRANCIA"));
        airports.add(new Airport("LILONGWE", "LLW", "MALAWI"));
        airports.add(new Airport("LIMA", "LIM", "PERU"));
        airports.add(new Airport("LINZ", "LNZ", "AUSTRIA"));
        airports.add(new Airport("LISBOA", "LIS", "PORTUGAL"));
        airports.add(new Airport("LIUBLIANA", "LIU", "ESLOVENIA"));
        airports.add(new Airport("LOME", "LFW", "TOGO"));
        airports.add(new Airport("LONCOPUE", "LCP", "ARGENTINA"));
        airports.add(new Airport("LONDON HEATHROW", "LHR", "INGLATERRA"));
        airports.add(new Airport("LOS ANGELES", "LAX", "ESTADOS UNIDOS"));
        airports.add(new Airport("LOS ANGELES", "LSQ", "CHILE"));
        airports.add(new Airport("LUANDA", "LAD", "ANGOLA"));
        airports.add(new Airport("LUBUMBASHI", "FBM", "ZAIRE"));
        airports.add(new Airport("LUSAKA", "LUN", "ZAMBIA"));
        airports.add(new Airport("LUXEMBURGO", "LUX", "LUXEMBURGO"));
        airports.add(new Airport("LYON", "LYS", "FRANCIA"));
        airports.add(new Airport("MACAPA", "MCP", "BRASIL"));
        airports.add(new Airport("MACELO", "MCZ", "BRASIL"));
        airports.add(new Airport("MADRAS", "MAA", "INDIA"));
        airports.add(new Airport("MADRID", "MAD", "ESPAÃ‘A"));
        airports.add(new Airport("MALAGA", "AGP", "ESPAÃ‘A"));
        airports.add(new Airport("MALARGUE", "MLG", "ARGENTINA"));
        airports.add(new Airport("MALMO", "MMA", "SUECIA"));
        airports.add(new Airport("MALTA", "MLA", "MALTA"));
        airports.add(new Airport("MANAGUA", "MGA", "NICARAGUA"));
        airports.add(new Airport("MANAOS",  "MO", "BRASIL"));
        airports.add(new Airport("MANILA", "MNL", "FILIPINAS"));
        airports.add(new Airport("MAPUTO", "MPM", "MOZAMBIQUE"));
        airports.add(new Airport("MAR DEL PLATA", "MDQ", "ARGENTINA"));
        airports.add(new Airport("MARACAIBO", "MAR", "VENEZUELA"));
        airports.add(new Airport("MARSELLA", "MRS", "FRANCIA"));
        airports.add(new Airport("MASCATE", "MCT", "OMAN"));
        airports.add(new Airport("MAURICIO", "MRU", "IS. MAURICIO"));
        airports.add(new Airport("MEDELLIN", "MDE", "COLOMBIA"));
        airports.add(new Airport("MELBOURNE", "MEL", "AUSTRALIA"));
        airports.add(new Airport("MENDOZA", "MDZ", "ARGENTINA"));
        airports.add(new Airport("MEMPHIS", "MEM", "TENNESSE, U.S.A."));
        airports.add(new Airport("MERIDA", "MID", "MEXICO"));
        airports.add(new Airport("MEXICO", "MEX", "MEXICO"));
        airports.add(new Airport("MIAMI", "MIA", "ESTADOS UNIDOS"));
        airports.add(new Airport("MILANO MALPENSA", "MXP", "ITALIA"));
        airports.add(new Airport("MILWAUKEE", "MKE", "WISCONSIN, U.S.A."));
        airports.add(new Airport("MINNEAPOLIS", "MSP", "MINNESOTA, U.S.A."));
        airports.add(new Airport("MONROVIA", "MLW", "LIBERIA"));
        airports.add(new Airport("MONTEGO BAY", "MBJ", "JAMAICA"));
        airports.add(new Airport("MONTEVIDEO", "MVD", "URUGUAY"));
        airports.add(new Airport("MONTREAL", "VUL", "CANADA"));
        airports.add(new Airport("MOSCU", "MOW", "RUSIA"));
        airports.add(new Airport("MULHOUSE", "MLH", "FRANCIA"));
        airports.add(new Airport("MUNICH", "MUC", "ALEMANIA FEDERAL"));
        airports.add(new Airport("NADI", "NAN", "FIJI"));
        airports.add(new Airport("NAGOYA", "NGO", "JAPON"));
        airports.add(new Airport("NAPOLES", "NAP", "ITALIA"));
        airports.add(new Airport("NASHVILLE", "BNA", "TENNESSEE - USA"));
        airports.add(new Airport("NASSAU", "NAS", "BAHAMAS"));
        airports.add(new Airport("NATAL", "NAT", "RN, BRASIL"));
        airports.add(new Airport("NECOCHEA", "NC", "ARGENTINA"));
        airports.add(new Airport("NEUQUEN", "NON", "ARGENTINA"));
        airports.add(new Airport("NIAMEN", "NIM", "NIGERIA"));
        airports.add(new Airport("NICOSIA", "NIC", "CHIPRE"));
        airports.add(new Airport("NIUE", "IUE", "ISLA NIUE"));
        airports.add(new Airport("NIZA", "NCE", "FRANCIA"));
        airports.add(new Airport("NORFOLK", "ORF", "VIRGINIA - USA"));
        airports.add(new Airport("NOUAKCHOTT", "NKC", "MAURITANIA"));
        airports.add(new Airport("NOUMEA", "NOU", "NUEVA CALEDONIA"));
        airports.add(new Airport("NUEVA ORLEANS", "MYC", "ESTADOS UNIDOS"));
        airports.add(new Airport("JOHN F. KENNEDY", "JFK", "ESTADOS UNIDOS"));
        airports.add(new Airport("NDJAMENA", "NDJ", "CHAD"));
        airports.add(new Airport("ODESSA", "ODS", "C.E.I."));
        airports.add(new Airport("OKINAWA", "OKA", "JAPON"));
        airports.add(new Airport("OLAVARRIA", "OLC", "ARGENTINA"));
        airports.add(new Airport("OPORTO", "OPO", "PORTUGAL"));
        airports.add(new Airport("ORLANDO", "ORL", "ESTADOS UNIDOS"));
        airports.add(new Airport("OSAKA", "OSA", "JAPON"));
        airports.add(new Airport("OSLO", "OSL", "NORUEGA"));
        airports.add(new Airport("OSORNO", "ZOS", "CHILE"));
        airports.add(new Airport("OTTAWA", "YOW", "CANADA"));
        airports.add(new Airport("PAGO PAGO", "PPG", "SAMOA"));
        airports.add(new Airport("PALERMO", "PMO", "ITALIA"));
        airports.add(new Airport("PANAMA", "PTY", "PANAMA"));
        airports.add(new Airport("PAPETE", "PPT", "TAHTIE"));
        airports.add(new Airport("PRAMARIBO", "PBM", "SURINAM"));
        airports.add(new Airport("PARANA", "PRA", "ARGENTINA"));
        airports.add(new Airport("CHARLES DE GAULLE", "CDG", "FRANCIA"));
        airports.add(new Airport("PASO DE LOS LIBRES", "AOL", "ARGENTINA"));
        airports.add(new Airport("PEKIN", "PEK", "P.R.CHINA"));
        airports.add(new Airport("PENANG", "PEN", "MALASIA"));
        airports.add(new Airport("PERITO MORENO", "PMO", "ARGENTINA"));
        airports.add(new Airport("PERTH", "PER", "AUSTRALIA"));
        airports.add(new Airport("PIOENIX", "PHY", "ARIZONA, U.S.A."));
        airports.add(new Airport("PHUKET", "HKT", "TAILIONIA"));
        airports.add(new Airport("PINAMAR", "PAJ", "ARGENTINA"));
        airports.add(new Airport("PITTSBURG", "PIT", "PENNSYLVANIA, U.S.A"));
        airports.add(new Airport("PIURA", "PIU", "PERU"));
        airports.add(new Airport("POINTE A PITRE", "PTP", "GUADALUPE"));
        airports.add(new Airport("POINTA DELGADA", "PDL", "IS. AZORES, PORTUGAL"));
        airports.add(new Airport("PORLAMAR", "PMV", "VENEZUELA"));
        airports.add(new Airport("PORT AU PRINCE", "PAP", "HAITI"));
        airports.add(new Airport("PORT OF SPAIN", "POS", "TRINIDAD"));
        airports.add(new Airport("PORTLAND", "PDX", "OREGON, U.S.A."));
        airports.add(new Airport("PORTO ALEGRE", "POA", "BRASIL"));
        airports.add(new Airport("PORTO SANTO", "PXO", "IS. MADEIRA"));
        airports.add(new Airport("POSADAS", "PSS", "ARGENTINA"));
        airports.add(new Airport("PRAGA", "PRG", "CHECOSLOVAQUIA"));
        airports.add(new Airport("PUCALIPA", "PCL", "PERU"));
        airports.add(new Airport("PUERTO DESEADO", "PUD", "ARGENTINA"));
        airports.add(new Airport("PUERTO MADRYN", "DRY", "ARGENTINA"));
        airports.add(new Airport("PUERTO MALDONADO", "PEM", "PERU"));
        airports.add(new Airport("PUERTO MONTT", "PMC", "CHILE"));
        airports.add(new Airport("PUNTA ARENAS", "PUQ", "CHILE"));
        airports.add(new Airport("PUNTA CANA", "PUJ", "REP. DOMINICANA"));
        airports.add(new Airport("PUNTA DEL ESTE", "PDP", "URUGUAY"));
        airports.add(new Airport("P. DE MALLORCA", "PMI", "IS. BALEARES, ESPAÃ‘A"));
        airports.add(new Airport("QUITO", "UIO", "ECUADOR"));
        airports.add(new Airport("RALEIGH", "RDU", "CAROLINA DEL N., U."));
        airports.add(new Airport("RANGOON", "RGN", "BURMA"));
        airports.add(new Airport("RAROTONGA", "RAR", "COOK, PACIFICO SUR"));
        airports.add(new Airport("RECIFE", "REC", "BRASIL"));
        airports.add(new Airport("RECONQUISTA", "RCO", "ARGENTINA"));
        airports.add(new Airport("REGGIO - CALABRIA", "REG", "ITALIA"));
        airports.add(new Airport("RESISTENCIA", "RES", "ARGENTINA"));
        airports.add(new Airport("REYKJAVIK", "REK", "ISLANDIA"));
        airports.add(new Airport("RICHMOND", "RIC", "VIRGINIA, U.S.A."));
        airports.add(new Airport("RINCON DE LOS SAUCES", "RNS", "ARGENTINA"));
        airports.add(new Airport("RIO CUARTO", "RCU", "ARGENTINA"));
        airports.add(new Airport("RIO DE JANEIRO", "RIO", "BRASIL"));
        airports.add(new Airport("RIO GALLEGOS", "RGL", "ARGENTINA"));
        airports.add(new Airport("RIO GRANDE", "RGA", "ARGENTINA"));
        airports.add(new Airport("RIO MAYO", "ROY", "ARGENTINA"));
        airports.add(new Airport("RIO FURBIO", "RYO", "ARGENTINA"));
        airports.add(new Airport("RIYADH", "RUH", "ARABIA SAUDITA"));
        airports.add(new Airport("ROATAN", "RTB", "HONDURAS"));
        airports.add(new Airport("ROCHESTER", "ROC", "NEW YORK - USA"));
        airports.add(new Airport("ROMA", "ROM", "ITALIA"));
        airports.add(new Airport("ROSARIO", "ROS", "ARGENTINA"));
        airports.add(new Airport("SAINT DENIS", "RUN", "IS. REUNION, FRANCIA"));
        airports.add(new Airport("SALPAN", "SPN", "ISLAS MARIANAS"));
        airports.add(new Airport("SALT LAKE CITY", "SLC", "UTAH, U.S.A."));
        airports.add(new Airport("SALTA", "SLA", "ARGENTINA"));
        airports.add(new Airport("SALVADOR", "SSA", "BRASIL"));
        airports.add(new Airport("SALZBURGO", "SZG", "AUSTRIA"));
        airports.add(new Airport("SAN ANTONIO OESTE", "OES", "ARGENTINA"));
        airports.add(new Airport("SAN DIEGO", "SAN", "CALIFORNIA, U.S.A."));
        airports.add(new Airport("SAN FRANCISCO", "SFO", "ESTADOS UNIDOS"));
        airports.add(new Airport("SAN JOSE", "SJC", "CALIFORNIA - USA"));
        airports.add(new Airport("SAN JOSE", "SJO", "COSTA RICA"));
        airports.add(new Airport("SAN JUAN", "SJU", "PUERTO RICO"));
        airports.add(new Airport("SAN JUAN", "UAQ", "ARGENTINA"));
        airports.add(new Airport("SAN JULIAN", "ULA", "ARGENTINA"));
        airports.add(new Airport("SAN LUIS", "LIQ", "ARGENTINA"));
        airports.add(new Airport("SAN PEDRO SULA", "SAP", "HONDURAS"));
        airports.add(new Airport("SAN RAFAEL", "AFA", "ARGENTINA"));
        airports.add(new Airport("SAN SALVADOR", "SAL", "EL SALVADOR"));
        airports.add(new Airport("SANAA", "SAH", "REP. ARABE DEL YEMEN"));
        airports.add(new Airport("SANTA CRUZ", "RZA", "ARGENTINA"));
        airports.add(new Airport("SANTA CRUZ", "SRZ", "BOLIVIA"));
        airports.add(new Airport("SANTA FE", "STN", "ARGENTINA"));
        airports.add(new Airport("SANTA MARTA", "SMR", "COLOMBIA"));
        airports.add(new Airport("SANTA ROSA", "RSA", "ARGENTINA"));
        airports.add(new Airport("SANTA TERESITA", "SST", "ARGENTINA"));
        airports.add(new Airport("SANTIAGO DE CHILE", "SCL", "CHILE"));
        airports.add(new Airport("SANTIAGO DE COMPOSTELA", "SCO", "ESPAÃ‘A"));
        airports.add(new Airport("SANTIAGO DEL ESTERO", "SDE", "ARGENTINA"));
        airports.add(new Airport("SANTO DOMINGO", "SDQ", "REP. DOMINICANA"));
        airports.add(new Airport("SAO LUIZ", "SLZ", "MA, BRASIL"));
        airports.add(new Airport("SAO PAULO-GUARULHOS", "GRU", "BRASIL"));
        airports.add(new Airport("SEATTLE", "SEA", "ESTADOS UNIDOS"));
        airports.add(new Airport("SEUL", "SEL", "COREA DEL SUR"));
        airports.add(new Airport("SEYILLA", "SVQ", "ESPAÃ‘A"));
        airports.add(new Airport("SHANGHAI", "SHA", "CHINA"));
        airports.add(new Airport("SHANNON", "SNN", "IRLANDA"));
        airports.add(new Airport("SHARJAH", "SHJ", "EMIR. ARABES"));
        airports.add(new Airport("SIERRA GRANDE", "SGV", "ARGENTINA"));
        airports.add(new Airport("SINGAPUR", "SIN", "SINGAPUR"));
        airports.add(new Airport("SOFIA", "SOF", "BULGARIA"));
        airports.add(new Airport("ST LUCIA", "SUJ", "SANTA LUCIA, ANTILLAS"));
        airports.add(new Airport("STAVANGER", "SVG", "NORUEGA"));
        airports.add(new Airport("STUTTGART", "STR", "ALEMANIA"));
        airports.add(new Airport("ST. CROIX", "STX", "IS. VIRGENES"));
        airports.add(new Airport("ST. LOUIS", "STL", "MISSOURI, U.S.A."));
        airports.add(new Airport("ST. MARITEN", "SXN", "ANTILLAS HOLANDESAS"));
        airports.add(new Airport("ST. THOMAS", "STT", "IS. VIRGENES"));
        airports.add(new Airport("SYDNEY", "SYD", "AUSTRALIA"));
        airports.add(new Airport("TABATINGA", "TBT", "BRASIL"));
        airports.add(new Airport("TACNA", "TCQ", "PERU"));
        airports.add(new Airport("TAIPEI", "TPE", "TAIWAN"));
        airports.add(new Airport("TAMPA", "TPA", "FLORIDA, U.S.A."));
        airports.add(new Airport("TANDIL", "NDL", "ARGENTINA"));
        airports.add(new Airport("TANDIL", "TTL", "ARGENTINA"));
        airports.add(new Airport("TANGER", "TNG", "MARRUECOS"));
        airports.add(new Airport("TARIJA", "TJA", "BOLIVIA"));
        airports.add(new Airport("TEFE", "TFF", "BRASIL"));
        airports.add(new Airport("TEGUCIGALPA", "TGU", "HONDURAS"));
        airports.add(new Airport("TEHERAN", "THR", "IRAN"));
        airports.add(new Airport("TEL AVIV", "TLV", "ISRAEL"));
        airports.add(new Airport("TEMUCO", "ZCO", "CHILE"));
        airports.add(new Airport("TENEGUE", "TCI", "ISLAS CANARIAS"));
        airports.add(new Airport("TERCEIRA", "TER", "IS. AZORES"));
        airports.add(new Airport("TESALONICA", "SKG", "GRECIA"));
        airports.add(new Airport("TIMISOALA", "TSR", "RUMANIA"));
        airports.add(new Airport("TIRANA", "TIA", "ALBANIA"));
        airports.add(new Airport("TOKYO", "TYO", "JAPON"));
        airports.add(new Airport("TONGATAPU", "TBU", "ISLAS TONGA"));
        airports.add(new Airport("TORONTO", "YYZ", "CANADA"));
        airports.add(new Airport("TRELEW", "REL", "ARGENTINA"));
        airports.add(new Airport("TRIPOLL", "TIP", "LIBIA"));
        airports.add(new Airport("TRUJILLO", "TRU", "PERU"));
        airports.add(new Airport("TUCSON", "TUS", "ARIZONA, U.S.A."));
        airports.add(new Airport("TUCUMAN", "TUC", "ARGENTINA"));
        airports.add(new Airport("TUNEZ", "TUN", "TUNICIA"));
        airports.add(new Airport("TURIN", "TRN", "ITALIA"));
        airports.add(new Airport("USHUAIA", "USH", "ARGENTINA"));
        airports.add(new Airport("VALDIVIA", "ZAL", "CHILE"));
        airports.add(new Airport("VALENCIA", "VLC", "ESPAÃ‘A"));
        airports.add(new Airport("VANCOUVER", "VVR", "CANADA"));
        airports.add(new Airport("VARADERO", "VRA", "CUBA"));
        airports.add(new Airport("VARSOVIA", "WAW", "POLONIA"));
        airports.add(new Airport("VENECIA", "VCE", "ITALIA"));
        airports.add(new Airport("VICTORIA", "YYJ", "CANADA"));
        airports.add(new Airport("VICTORIA FALLS", "VFA", "ZIMBAWE"));
        airports.add(new Airport("VIEDMA", "VDM", "ARGENTINA"));
        airports.add(new Airport("VIENA", "VIE", "AUSTRIA"));
        airports.add(new Airport("VIGO", "VGO", "ESPAÃ‘A"));
        airports.add(new Airport("VILLA GESELL", "VLG", "ARGENTINA"));
        airports.add(new Airport("VILLA MERCEDES", "VME", "ARGENTINA"));
        airports.add(new Airport("VILNA", "VMO", "LITUANIA"));
        airports.add(new Airport("VINA DEL MAR", "KNA", "CHILE"));
        airports.add(new Airport("WASHINGTON D. C.", "WAS", "ESTADOS UNIDOS"));
        airports.add(new Airport("WELLINGTON", "WLG", "NUEVA ZELANDA"));
        airports.add(new Airport("WINDHOEK", "WDH", "NAMIBIA"));
        airports.add(new Airport("WINDSOR", "YOG", "CANADA"));
        airports.add(new Airport("WINNIPEG", "YWG", "CANADA"));
        airports.add(new Airport("XIAMEN", "XMN", "REPUBLICA POP. CHINA"));
        airports.add(new Airport("ZAGREB", "ZAG", "CROACIA"));
        airports.add(new Airport("ZAPALA", "APZ", "ARGENTINA"));
        airports.add(new Airport("ZURICH", "ZRH", "SUIZA"));
    }

    @Override
    public List<Airport> getLocation(String name) {
        List<Airport> filterAirports = airports;
        filterAirports = new ArrayList<Airport>(filterAirports.stream().filter(airport -> airport.getCity().toLowerCase().contains(name.toLowerCase()) || airport.getCode().toLowerCase().contains(name.toLowerCase()) || airport.getCountry().toLowerCase().contains(name.toLowerCase())).toList());
        return filterAirports;
    }
}
