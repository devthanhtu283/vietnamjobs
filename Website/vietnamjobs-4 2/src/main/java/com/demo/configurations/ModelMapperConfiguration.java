package com.demo.configurations;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.demo.dtos.AccountDTO;
import com.demo.dtos.AdminDTO;
import com.demo.dtos.ApplicationHistoryDTO;
import com.demo.dtos.CheckNewDTO;
import com.demo.dtos.EmployerDTO;
import com.demo.dtos.FollowDB;
import com.demo.dtos.FollowDTO;
import com.demo.dtos.KeywordDTO;
import com.demo.dtos.PostingDTO;
import com.demo.dtos.PostingspaymentDTO;
import com.demo.dtos.SeekerDTO;
import com.demo.dtos.TransactionHistoryDTO;
import com.demo.entities.Account;
import com.demo.entities.Admin;
import com.demo.entities.ApplicationHistory;
import com.demo.entities.Category;
import com.demo.entities.CheckNews;
import com.demo.entities.Employer;
import com.demo.entities.Experience;
import com.demo.entities.Follow;
import com.demo.entities.Keyword;
import com.demo.entities.Local;
import com.demo.entities.Postings;
import com.demo.entities.Postingspayment;
import com.demo.entities.Rank;
import com.demo.entities.Seeker;
import com.demo.entities.TransactionHistory;
import com.demo.entities.Type;
import com.demo.entities.TypeAccount;
import com.demo.entities.Wage;
import com.demo.services.CategoryService;
import com.demo.services.EmployerService;
import com.demo.services.ExperienceService;
import com.demo.services.LocalService;
import com.demo.services.RankService;
import com.demo.services.TypeAccountService;
import com.demo.services.TypeService;
import com.demo.services.WageService;
import com.demo.servicesModelMap.ServiceModelMap;


@Configuration
public class ModelMapperConfiguration {
	@Autowired
	private Environment environment;

	@Autowired
	private ServiceModelMap serviceModelMap;

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();

		// convert typeaccount
//		Converter<String, TypeAccount> converterTypeAccountNameToTypeAccount = new AbstractConverter<String, TypeAccount>() {
//			@Override
//			protected TypeAccount convert(String source) {
//				TypeAccount typeAccount = serviceModelMap.findbytypeaccountname(source);
//				return typeAccount;
//			}
//		};
//		mapper.typeMap(AccountDTO.class, Account.class).addMappings(m -> {
//			m.using(converterTypeAccountNameToTypeAccount).map(AccountDTO::getTypeAccount, Account::setTypeAccount);
//		});
		
		
		// convert AccountID to Account
		Converter<Integer, Postings> converterPostingIdToPostings = new AbstractConverter<Integer, Postings>() {
			@Override
			protected Postings convert(Integer source) {
				Postings postings = new Postings();
				postings.setId(source);
				return postings;
			}
		};
		mapper.typeMap(PostingspaymentDTO.class, Postingspayment.class).addMappings(m -> {
			m.using(converterPostingIdToPostings).map(PostingspaymentDTO::getPostingsid, Postingspayment::setPostings);
		});
		Converter<Integer, Account> converterAccountIdToAccount = new AbstractConverter<Integer, Account>() {
			@Override
			protected Account convert(Integer source) {
				Account account = new Account();
				account.setId(source);
				return account;
			}
		};
		mapper.typeMap(AdminDTO.class, Admin.class).addMappings(m -> {
			m.using(converterAccountIdToAccount).map(AdminDTO::getAccountId, Admin::setAccount);
		});

		mapper.typeMap(SeekerDTO.class, Seeker.class).addMappings(m -> {
			m.using(converterAccountIdToAccount).map(SeekerDTO::getAccountID, Seeker::setAccount);
		});
		
		mapper.typeMap(EmployerDTO.class, Employer.class).addMappings(m -> {
			m.using(converterAccountIdToAccount).map(EmployerDTO::getAccountId, Employer::setAccount);
		});
		
		// convert CateName to Category
		Converter<String, Category> converterCategoryNameToCategory = new AbstractConverter<String, Category>() {
			@Override
			protected Category convert(String source) {
				Category category = serviceModelMap.findbycategoryname(source);
				return category;
			}
		};
		// convert EmployerName to Employer
		Converter<String, Employer> converterEmployerNameToEmployer = new AbstractConverter<String, Employer>() {
			@Override
			protected Employer convert(String source) {
				Employer employer = serviceModelMap.findbyemployername(source);
				return employer;
			}
		};
		// convert ExperienceName to Employer
		Converter<String, Experience> converterExperienceNameToExperience = new AbstractConverter<String, Experience>() {
			@Override
			protected Experience convert(String source) {
				Experience experience = serviceModelMap.findbyexpname(source);
				return experience;
			}
		};
		// convert LocalName to Local
		Converter<String, Local> converterLocalNameToLocal = new AbstractConverter<String, Local>() {
			@Override
			protected Local convert(String source) {
				Local local = serviceModelMap.findbylocalname(source);
				return local;
			}
		};
		// convert RankName to Rank
		Converter<String, Rank> converterRankNameToRank = new AbstractConverter<String, Rank>() {
			@Override
			protected Rank convert(String source) {
				Rank rank = serviceModelMap.findbyrankname(source);
				return rank;
			}
		};
		// convert TypeName to Type
		Converter<String, Type> converterTypeNameToType = new AbstractConverter<String, Type>() {
			@Override
			protected Type convert(String source) {
				Type type = serviceModelMap.findbytypename(source);
				return type;
			}
		};
		// convert WageName to Wage
		Converter<String, Wage> converterWageNameToWage = new AbstractConverter<String, Wage>() {
			@Override
			protected Wage convert(String source) {
				Wage wage = serviceModelMap.findbywagename(source);
				return wage;
			}
		};

		mapper.typeMap(PostingDTO.class, Postings.class).addMappings(m -> {
			m.using(converterCategoryNameToCategory).map(PostingDTO::getCategoryName, Postings::setCategory);
			m.using(converterEmployerNameToEmployer).map(PostingDTO::getEmployerName, Postings::setEmployer);
			m.using(converterExperienceNameToExperience).map(PostingDTO::getExpName, Postings::setExperience);
			m.using(converterLocalNameToLocal).map(PostingDTO::getLocalName, Postings::setLocal);
			m.using(converterRankNameToRank).map(PostingDTO::getRankName, Postings::setRank);
			m.using(converterTypeNameToType).map(PostingDTO::getTypeName, Postings::setType);
			m.using(converterWageNameToWage).map(PostingDTO::getWageName, Postings::setWage);
		});
		

		mapper.addMappings(new PropertyMap<Account, AccountDTO>() {
			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setUsername(source.getUsername());
				map().setEmail(source.getEmail());
				map().setSecurityCode(source.getSecurityCode());
				map().setCreated(source.getCreated());
				map().setStatus(source.isStatus());
				map().setTypeAccount(source.getTypeAccount().getName());
				map().setTypeAccountID(source.getTypeAccount().getId());
				map().setWallet(source.getWallet());
				map().setPassword(source.getPassword());

			}
		});

		mapper.addMappings(new PropertyMap<AccountDTO, Account>() {
			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setCreated(source.getCreated());
				map().setEmail(source.getEmail());
				map().setPassword(source.getPassword());
				map().setSecurityCode(source.getSecurityCode());
				map().setStatus(source.isStatus());
				map().setUsername(source.getUsername());
				map().setWallet(source.getWallet());
			}
		});

		mapper.addMappings(new PropertyMap<Admin, AdminDTO>() {
			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setAccountId(source.getAccount().getId());
				map().setAccountName(source.getAccount().getUsername());
				map().setFullname(source.getFullname());
				map().setPhone(source.getPhone());
				map().setPhoto(source.getPhoto());

			}
		});
		mapper.addMappings(new PropertyMap<AdminDTO, Admin>() {
			@Override
			protected void configure() {
				map().setFullname(source.getFullname());
				map().setId(source.getId());
				map().setPhone(source.getPhone());
				map().setPhoto(source.getPhoto());
			}
		});

		mapper.addMappings(new PropertyMap<Seeker, SeekerDTO>() {
			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setAccountID(source.getAccount().getId());
				map().setAccountName(source.getAccount().getUsername());
				map().setCvInformation(source.getCvInformation());
				map().setFullName(source.getFullname());
				map().setStatus(source.isStatus());
				map().setDescription(source.getDescription());
				map().setPhone(source.getPhone());
				map().setAvatar(source.getAvatar());
			
			}
		});
		mapper.addMappings(new PropertyMap<SeekerDTO, Seeker>() {
			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setAvatar(source.getAvatar());
				map().setCvInformation(source.getCvInformation());
				map().setDescription(source.getDescription());
				map().setFullname(source.getFullName());
				map().setPhone(source.getPhone());
				map().setStatus(source.isStatus());

			}
		});

		mapper.addMappings(new PropertyMap<Employer, EmployerDTO>() {
			@Override
			protected void configure() {
				// TODO Auto-generated method stub
				map().setAccountId(source.getAccount().getId());
				map().setAccountName(source.getAccount().getUsername());
				map().setAddress(source.getAddress());
				map().setCover(source.getCover());
				map().setDescription(source.getDescription());
				map().setFolow(0);
				map().setId(source.getId());
				map().setLink(source.getLink());
				map().setLogo(source.getLogo());
				map().setMapLink(source.getMapLink());
				map().setName(source.getName());
				map().setScale(source.getScale());
				map().setStatus(source.isStatus());
			}
		});
		
		mapper.addMappings(new PropertyMap<EmployerDTO, Employer>() {

			@Override
			protected void configure() {
				// TODO Auto-generated method stub
				map().setAddress(source.getAddress());
				map().setCover(source.getCover());
				map().setDescription(source.getDescription());
				map().setId(source.getId());
				map().setLink(source.getLink());
				map().setLogo(source.getLogo());
				map().setMapLink(source.getMapLink());
				map().setName(source.getName());
				map().setScale(source.getScale());
				map().setStatus(source.isStatus());
				
			}
		});

		mapper.addMappings(new PropertyMap<Postings, PostingDTO>() {
			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setEmployerName(source.getEmployer().getName());
				map().setTitle(source.getTitle());
				map().setDescription(source.getDescription());
				map().setCreated(source.getCreated());
				map().setDealine(source.getDeadline());
				map().setGender(source.getGender());
				map().setQuantity(source.getQuantity());
				map().setWageName(source.getWage().getName());
				map().setCategoryName(source.getCategory().getName());
				map().setLocalName(source.getLocal().getName());
				map().setRankName(source.getRank().getName());
				map().setTypeName(source.getType().getName());
				map().setExpName(source.getExperience().getName());
				map().setStatus(source.isStatus());
				map().setEmployerLogo(source.getEmployer().getLogo());

			}
		});
		mapper.addMappings(new PropertyMap<PostingDTO, Postings>() {
			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setCreated(source.getCreated());
				map().setDeadline(source.getDealine());
				map().setDescription(source.getDescription());
				map().setGender(source.getGender());
				map().setQuantity(source.getQuantity());
				map().setStatus(source.isStatus());
				map().setTitle(source.getTitle());

			}
		});
//		thanh tu them vao
		mapper.addMappings(new PropertyMap<ApplicationHistory, ApplicationHistoryDTO>() {

			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setEmployerName(source.getPostings().getEmployer().getName());
				map().setPostingTitle(source.getPostings().getTitle());
				map().setCreate(source.getCreated());
				map().setPostingCreated(source.getPostings().getCreated());
				map().setPostingDeadline(source.getPostings().getDeadline());
				map().setResult(source.getResult());
				map().setStatus(source.getStatus());
				map().setSeekerName(source.getSeeker().getFullname());
				map().setSeekerID(source.getSeeker().getId());
				map().setSeekerCV(source.getSeeker().getCvInformation());
				map().setPostingID(source.getPostings().getId());
				map().setSeekerEmail(source.getSeeker().getAccount().getEmail());
				
			}
			
		});
		mapper.addMappings(new PropertyMap<ApplicationHistoryDTO, ApplicationHistory>() {

			@Override
			protected void configure() {
				map().setId(source.getId());
				map().setCreated(source.getCreate());
				map().setStatus(source.getStatus());
				map().setResult(source.getResult());
			}
			
		});
		  
		  mapper.addMappings(new PropertyMap<CheckNews, CheckNewDTO>() {
				@Override
				protected void configure() {
					map().setId(source.getId());
					map().setPostingsid(source.getPostings().getId());
					map().setSeekerid(source.getSeeker().getId());
					map().setStatus(source.isStatus());
				}
			});
			mapper.addMappings(new PropertyMap<CheckNewDTO, CheckNews>() {
				@Override
				protected void configure() {
					map().setId(source.getId());
					map().setStatus(source.isStatus());
				}
			});
//			mapper.addMappings(new PropertyMap<Follow, FollowDTO>() {
//				@Override
//				protected void configure() {
//					map().setId(source.getId());
//					map().setEmployerid(source.getEmployer().getId());
//					map().setSeekerid(source.getSeeker().getId());
//					map().setStatus(source.isStatus());
//				}
//			});
			mapper.addMappings(new PropertyMap<Follow, FollowDTO>() {

				@Override
				protected void configure() {
					map().setId(source.getId());
					map().setEmployerName(source.getEmployer().getName());
					map().setSeekerName(source.getSeeker().getFullname());
					map().setStatus(source.isStatus());

				}
				   
			});
			
			mapper.addMappings(new PropertyMap<TransactionHistory, TransactionHistoryDTO>() {
				@Override
				protected void configure() {
					map().setId(source.getId());
					map().setAccountid(source.getAccount().getId());
					map().setStatus(source.isStatus());
					map().setCreated(source.getCreated());
					map().setTotal(source.getTotal());
					map().setTradingCode(source.getTradingCode());
				}
			});

			mapper.addMappings(new PropertyMap<TransactionHistoryDTO, TransactionHistory>() {
				@Override
				protected void configure() {
					map().setId(source.getId());
					map().setStatus(source.isStatus());
					map().setCreated(source.getCreated());
					map().setTotal(source.getTotal());
					map().setTradingCode(source.getTradingCode());
				}
			});
			mapper.addMappings(new PropertyMap<Keyword, KeywordDTO>() {
				@Override
				protected void configure() {
					map().setId(source.getId());
					map().setCategoryid(source.getCategory().getId());
					map().setName(source.getName());
					map().setStatus(source.isStatus());
				}
			});
			mapper.addMappings(new PropertyMap<KeywordDTO, Keyword>() {
				@Override
				protected void configure() {
					map().setId(source.getId());
					map().setName(source.getName());
					map().setStatus(source.isStatus());
				}
			});

			mapper.addMappings(new PropertyMap<Postingspayment, PostingspaymentDTO>() {
				@Override
				protected void configure() {
					map().setId(source.getId());
					map().setPostingsid(source.getPostings().getId());
					map().setCost(source.getCost());
					map().setCreated(source.getCreated());
					map().setStatus(source.isStatus());
					map().setDuration(source.getDuration());
				}
			});
			mapper.addMappings(new PropertyMap<PostingspaymentDTO, Postingspayment>() {
				@Override
				protected void configure() {
					map().setId(source.getId());
					map().setCost(source.getCost());
					map().setStatus(source.isStatus());
					map().setDuration(source.getDuration());
					map().setCreated(source.getCreated());
				}
			});

		return mapper;
	}
}